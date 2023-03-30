package dev.rockstar.portfolio.ui.addedit

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
import dev.rockstar.portfolio.databinding.LayoutAddEditBinding
import timber.log.Timber

@AndroidEntryPoint
class AddEditFragment : BindingFragment<LayoutAddEditBinding>(R.layout.layout_add_edit) {

    @get:VisibleForTesting
    internal val viewModel: AddEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {}.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_edit_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.save) {
                    // save group data to database
                    Timber.d("onMenuItemSelected: SAVE")
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}