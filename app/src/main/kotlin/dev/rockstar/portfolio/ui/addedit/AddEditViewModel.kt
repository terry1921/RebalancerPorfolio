package dev.rockstar.portfolio.ui.addedit

import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.core.data.repository.GroupRepository
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

}