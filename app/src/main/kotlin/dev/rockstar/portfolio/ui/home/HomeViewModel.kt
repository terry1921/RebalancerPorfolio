package dev.rockstar.portfolio.ui.home

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.core.data.repository.AssetRepository
import dev.rockstar.portfolio.core.model.Asset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val assetRepository: AssetRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var isNetworkAvailable: Boolean by bindingProperty(false)
        private set

    init {
        Timber.d("HomeViewModel initialized")
    }

    private val fetching: MutableStateFlow<Int> = MutableStateFlow(0)
    private val listFlow = fetching.flatMapLatest { page ->
        Timber.d("fetching page: $page")
        assetRepository.fetchData(
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { isNetworkAvailable = true }
        )
    }

    @get:Bindable
    val assetsList: List<Asset> by listFlow.asBindingProperty(viewModelScope, emptyList())

}