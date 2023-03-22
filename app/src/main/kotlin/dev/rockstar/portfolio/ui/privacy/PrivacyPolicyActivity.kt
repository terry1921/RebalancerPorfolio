package dev.rockstar.portfolio.ui.privacy

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutPrivacyBinding

class PrivacyPolicyActivity : BindingActivity<LayoutPrivacyBinding>(R.layout.layout_privacy) {

    @VisibleForTesting
    internal val viewModel: PrivacyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            vm = viewModel
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        val systemService = getSystemService(Context.CONNECTIVITY_SERVICE)
        viewModel.checkConnection(systemService)
    }


    override fun onResume() {
        super.onResume()
        val url = getString(R.string.privacy_policy_url)
        binding.webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }


}