package com.example.pocwebviewapp.webintegration

import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import androidx.fragment.app.viewModels
import com.example.pocwebviewapp.BaseFragment
import com.example.pocwebviewapp.R
import com.example.pocwebviewapp.databinding.FragmentWebViewIntegrationBinding

class WebViewIntegrationFragment :
    BaseFragment<WebViewIntegrationViewModel, FragmentWebViewIntegrationBinding>() {

    override val viewModel: WebViewIntegrationViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_web_view_integration

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupViews() {
        binding.webView.settings.apply {
            javaScriptEnabled = true
            displayZoomControls = false
            domStorageEnabled = true
            setSupportZoom(false)
        }
        binding.webView.addJavascriptInterface(this, "Android")
        binding.webView.loadUrl("file:///android_asset/index.html")
    }

    @JavascriptInterface
    fun onFinished() {
        viewModel.navigateBack()
    }

    override fun setupListeners() {}

    override fun setupObservers() {}
}