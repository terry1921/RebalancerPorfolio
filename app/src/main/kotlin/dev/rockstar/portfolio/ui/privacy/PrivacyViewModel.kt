package dev.rockstar.portfolio.ui.privacy

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.R
import javax.inject.Inject

@HiltViewModel
class PrivacyViewModel @Inject constructor() : BindingViewModel() {

    @get:Bindable
    var message: Int by bindingProperty(-1)
        private set

    fun checkConnection(systemService: Any?) {
        systemService.whatIfNotNull {
            var result = false
            val connectivityManager = (systemService as ConnectivityManager)
            connectivityManager.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> {
                            false
                        }
                    }
                }
            }
            if (!result) {
                message = R.string.please_check_connection
            }
        }
    }

}