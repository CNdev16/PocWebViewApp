package com.example.pocwebviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

sealed class NavigateCommand {
    data class To(val direction: NavDirections) : NavigateCommand()
    data object Back : NavigateCommand()
}

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    protected lateinit var binding: Binding

    protected abstract val viewModel: ViewModel

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupListeners()
        setupObservers()

        onBackPressed()
        observeNavigationAction()
    }

    abstract fun setupViews()

    abstract fun setupListeners()

    abstract fun setupObservers()

    private fun observeNavigationAction() {
        viewModel.navigation.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.run(::navigate)
        }
    }

    private fun navigate(command: NavigateCommand) {
        when (command) {
            is NavigateCommand.To -> findNavController().navigate(command.direction)
            NavigateCommand.Back -> findNavController().popBackStack()
        }
    }

    open fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (findNavController().currentBackStackEntry?.destination?.id == R.id.homeFragment)
                        requireActivity().finishAffinity()
                    else viewModel.navigateBack()
                }
            }
        )
    }
}