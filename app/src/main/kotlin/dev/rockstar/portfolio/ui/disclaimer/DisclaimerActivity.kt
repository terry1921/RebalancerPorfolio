package dev.rockstar.portfolio.ui.disclaimer

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutDisclaimerBinding
import dev.rockstar.portfolio.ui.main.MainActivity
import dev.rockstar.portfolio.ui.privacy.PrivacyPolicyActivity

class DisclaimerActivity : BindingActivity<LayoutDisclaimerBinding>(R.layout.layout_disclaimer) {

    @get:VisibleForTesting
    internal val viewModel: DisclaimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            vm = viewModel
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isShowedDisclaimer()) {
            goToMain()
        }
        binding.linkPrivacyPolice.setOnClickListener {
            val intent = Intent(this@DisclaimerActivity, PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }
        binding.startButton.setOnClickListener {
            viewModel.showedDisclaimer()
            goToMain()
        }
    }

    private fun goToMain() {
        val intent = Intent(this@DisclaimerActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}