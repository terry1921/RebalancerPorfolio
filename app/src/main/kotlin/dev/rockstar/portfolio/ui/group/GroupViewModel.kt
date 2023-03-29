package dev.rockstar.portfolio.ui.group

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.core.data.repository.GroupRepository
import dev.rockstar.portfolio.core.model.Group
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var isNetworkAvailable: Boolean by bindingProperty(false)
        private set

    init {
        Timber.d("GroupsViewModel initialized")
    }

    private val fetching: MutableStateFlow<Int> = MutableStateFlow(0)
    private val listFlow = fetching.flatMapLatest { page ->
        Timber.d("fetching page: $page")
        groupRepository.fetchData(
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = {
                it?.let { Timber.e(it) }
                isNetworkAvailable = true
            }
        )
    }

    @get:Bindable
    val groupList: List<Group> by listFlow.asBindingProperty(viewModelScope, emptyList())

}