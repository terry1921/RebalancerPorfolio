package dev.rockstar.portfolio.ui.home

import android.os.Bundle
import android.view.*
import androidx.annotation.VisibleForTesting
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutHomeBinding
import timber.log.Timber

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
            adapter = AssetAdapter()
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.add) {
                    // nav controller navigate add from home
                    Timber.d("onMenuItemSelected: ADD")
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}