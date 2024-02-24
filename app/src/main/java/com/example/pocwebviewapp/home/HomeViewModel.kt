package com.example.pocwebviewapp.home

import com.example.pocwebviewapp.BaseViewModel
import com.example.pocwebviewapp.NavigateCommand

class HomeViewModel : BaseViewModel() {

    fun navigateToWebViewIntegration() {
        HomeFragmentDirections.actionHomeFragmentToWebViewIntegrationFragment()
            .run(NavigateCommand::To)
            .run(::navigate)
    }
}
