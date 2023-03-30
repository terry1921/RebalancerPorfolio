package dev.rockstar.portfolio.ui.group

import android.os.Bundle
import android.view.*
import androidx.annotation.VisibleForTesting
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutGroupBinding
import dev.rockstar.portfolio.utils.FROM_GROUP
import timber.log.Timber

@AndroidEntryPoint
class GroupFragment : BindingFragment<LayoutGroupBinding>(R.layout.layout_group) {

    @get:VisibleForTesting
    internal val viewModel: GroupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            adapter = GroupAdapter()
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
                    Timber.d("onMenuItemSelected: ADD")
                    val action = GroupFragmentDirections.actionAdd(FROM_GROUP)
                    findNavController().navigate(action)
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}