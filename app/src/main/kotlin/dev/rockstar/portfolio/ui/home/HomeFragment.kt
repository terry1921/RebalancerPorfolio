package dev.rockstar.portfolio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutHomeBinding

@AndroidEntryPoint
class HomeFragment : BindingFragment<LayoutHomeBinding>(R.layout.layout_home) {

    @get:VisibleForTesting
    internal val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            vm = viewModel
        }.root
    }

}