package dev.rockstar.portfolio.ui.privacy

import android.net.ConnectivityManager
import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.utils.ConnectionStatus
import javax.inject.Inject

@HiltViewModel
class PrivacyViewModel @Inject constructor() : BindingViewModel() {

    @get:Bindable
    var message: Int by bindingProperty(-1)
        private set

    fun checkConnection(systemService: Any?) {
        systemService.whatIfNotNull {
            val activeNetworkInfo = (systemService as ConnectivityManager).activeNetworkInfo
            val status =
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting) {
                    ConnectionStatus.NOT_CONNECTED
                } else if (activeNetworkInfo.type == 1) {
                    ConnectionStatus.CONNECTED_WIFI
                } else {
                    ConnectionStatus.CONNECTED
                }
            if (status == ConnectionStatus.NOT_CONNECTED) {
                message = R.string.please_check_connection
            }
        }
    }

}