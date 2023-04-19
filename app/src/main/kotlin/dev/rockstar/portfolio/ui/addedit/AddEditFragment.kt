package dev.rockstar.portfolio.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.databinding.Observable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutAddEditBinding
import dev.rockstar.portfolio.utils.From
import dev.rockstar.portfolio.utils.addProgressChangedListener
import timber.log.Timber
import kotlin.math.roundToInt

/**
 * The `AddEditFragment` class is a Fragment in a Kotlin app that handles the saving and loading of
 * group data, and updates corresponding variables.
 * It also provides a menu for saving the data.
 */
@AndroidEntryPoint
class AddEditFragment : BindingFragment<LayoutAddEditBinding>(R.layout.layout_add_edit) {

    @get:VisibleForTesting
    internal val viewModel: AddEditViewModel by viewModels()

    private val safeArgs: AddEditFragmentArgs by navArgs()
    private val from: From by lazy { safeArgs.from }
    private val id: Long by lazy { safeArgs.id }

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

    override fun onResume() {
        super.onResume()
        setUpSeekBarAllocation()
        viewModel.setFrom(from)
        if (id != 0L) viewModel.load(id)
    }

    private fun setUpSeekBarAllocation() {
        binding.seekbar.addProgressChangedListener { _, progress, fromUser ->
            if (fromUser) viewModel.allocation = progress.toString()
        }
        binding.allocation.addTextChangedListener { editable ->
            if (!editable.isNullOrEmpty()) {
                val progress = editable.toString().toFloat().roundToInt()
                if (progress > 100) {
                    binding.allocation.setText(R.string._100)
                    binding.seekbar.progress = 100
                    return@addTextChangedListener
                }
                binding.seekbar.progress = progress
            }
        }
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
                    validateData()
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewModel.isSaved.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Timber.d("isSaved: ${viewModel.isSaved.get()}")
                if (viewModel.isSaved.get()) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })

    }

    private fun validateData() {
        val name = binding.name.text
        if (name.isNullOrEmpty()) {
            binding.name.error = "Name is required"
            return
        }
        val allocation = binding.allocation.text
        if (allocation.isNullOrEmpty()) {
            binding.allocation.error = "Allocation is required"
            return
        }
        val note = binding.note.text
        viewModel.save(name.toString(), allocation.toString().toFloat(), note.toString())
    }

}