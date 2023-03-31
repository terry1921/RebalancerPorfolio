package dev.rockstar.portfolio.ui.addedit

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.annotation.VisibleForTesting
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutAddEditBinding
import dev.rockstar.portfolio.utils.FROM_GROUP
import dev.rockstar.portfolio.utils.FROM_HOME
import timber.log.Timber
import kotlin.math.roundToInt

@AndroidEntryPoint
class AddEditFragment : BindingFragment<LayoutAddEditBinding>(R.layout.layout_add_edit) {

    @get:VisibleForTesting
    internal val viewModel: AddEditViewModel by viewModels()

    private val safeArgs: AddEditFragmentArgs by navArgs()
    private val from: Int by lazy { safeArgs.from }
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
        if (from == FROM_GROUP) {
            initGroupUI()
        }
        if (from == FROM_HOME) {
            initHomeUI()
        }
        setUpSeekBarAllocation()
    }

    private fun setUpSeekBarAllocation() {
        binding.seekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.allocation = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        })
        binding.allocation.addTextChangedListener { editable ->
            if (editable != null && editable.isNotEmpty()) {
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

    private fun initGroupUI() {
        viewModel.setGroup()
    }

    private fun initHomeUI() {
        viewModel.setHome()
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