package dev.rockstar.portfolio.ui.addedit

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.core.data.repository.GroupRepository
import dev.rockstar.portfolio.core.model.Group
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : BindingViewModel() {

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

    fun setGroup() {
        isGroup = true
    }

    fun setHome() {
        isGroup = false
    }

    fun save(name: String, allocation: Float, note: String) {
        val group = Group(name, allocation, note)
        Timber.d("save -> group: $group")
        val insertFlow: Flow<Boolean?> = groupRepository.insertGroup(
            group = group,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { message = it }
        )
        viewModelScope.launch { insertFlow.collect { Timber.d("save -> value: $it") } }
    }

}