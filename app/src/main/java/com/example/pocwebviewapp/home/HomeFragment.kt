package com.example.pocwebviewapp.home

import androidx.fragment.app.viewModels
import com.example.pocwebviewapp.BaseFragment
import com.example.pocwebviewapp.R
import com.example.pocwebviewapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setupViews() {}

    override fun setupListeners() {
        binding.btnOpenWebView.setOnClickListener {
            viewModel.navigateToWebViewIntegration()
        }
    }

    override fun setupObservers() {}
}
