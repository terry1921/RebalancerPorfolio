package dev.rockstar.portfolio.ui.addedit

import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.core.data.repository.AssetRepository
import dev.rockstar.portfolio.core.data.repository.GroupRepository
import dev.rockstar.portfolio.core.model.Asset
import dev.rockstar.portfolio.core.model.Group
import dev.rockstar.portfolio.utils.From
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * The `AddEditViewModel` class is a ViewModel in a Kotlin app that handles the saving and loading of
 * group data, and updates corresponding variables.
 **/
@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val groupRepository: GroupRepository,
    private val assetRepository: AssetRepository
) : BindingViewModel() {

    var isSaved: ObservableBoolean = ObservableBoolean(false)

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var message: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isGroup: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var allocation: String by bindingProperty("0")

    @get:Bindable
    var name: String by bindingProperty("")

    @get:Bindable
    var note: String by bindingProperty("")

    @get:Bindable
    var isEdit: Boolean by bindingProperty(false)
        private set

    private var id: Long = 0L

    /**
     * The function saves a group object to a repository and updates it if it already exists.
     *
     * @param name A string representing the name of a group.
     * @param allocation Allocation is a Float type parameter that represents the amount of money
     * allocated to a group.
     * @param note The `note` parameter is a string that represents any additional notes or comments
     * related to the `Group` being saved.
     */
    fun save(name: String, allocation: Float, note: String) {
        val group = Group(name, allocation, note)
        Timber.d("save -> group: $group")
        val flow = if (isEdit) {
            group.id = id
            updateGroupFlow(group)
        } else {
            insertGroupFlow(group)
        }
        viewModelScope.launch {
            flow.collect { saved ->
                Timber.d("save -> value: $saved")
                saved?.let {
                    message = if (it) {
                        "Group saved"
                    } else {
                        "Group not saved"
                    }
                    isSaved.set(it)
                }
            }
        }
    }

    private fun insertGroupFlow(group: Group) =
        groupRepository.insertGroup(
            group = group,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { message = it }
        )

    private fun updateGroupFlow(group: Group) =
        groupRepository.updateGroup(
            group = group,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { message = it }
        )

    /**
     * The function loads a group with a given ID and sets a flag to indicate that it is being edited.
     *
     * @param id The `id` parameter is a `Long` value that represents the unique identifier of a group
     * that needs to be loaded. It is used as an input parameter for the `getGroup()` function of the
     * `groupRepository` object to retrieve the corresponding group data from a data source.
     */
    fun load(id: Long) {
        isEdit = true
        this.id = id
        Timber.d("load -> id: $id")
        if (isGroup) {
            val groupFlow: Flow<Group?> = groupRepository.getGroup(
                id = id,
                onStart = { isLoading = true },
                onComplete = { isLoading = false },
                onError = { message = it }
            )
            viewModelScope.launch { groupFlow.collect(::loadGroup) }
        } else {
            val assetFlow: Flow<Asset?> = assetRepository.getAsset(
                id = id,
                onStart = { isLoading = true },
                onComplete = { isLoading = false },
                onError = { message = it }
            )
            viewModelScope.launch { assetFlow.collect(::loadAsset) }
        }
    }

    private fun loadGroup(group: Group?) {
        Timber.d("loadGroup -> group: $group")
        group?.let { g ->
            name = g.name
            allocation = g.targetAllocation.toString()
            note = g.note
        }
    }

    private fun loadAsset(asset: Asset?) {
        Timber.d("loadAsset -> asset: $asset")
        asset?.let { a ->
            name = a.name
            allocation = a.targetAllocation.toString()
            note = a.note
        }
    }

    /**
     * The function sets the `isGroup` variable based on the `From` enum value passed to it.
     */
    fun setFrom(from: From) {
        isGroup = when (from) {
            From.HOME -> false
            From.GROUP -> true
            From.DEFAULT -> false
        }
    }

}