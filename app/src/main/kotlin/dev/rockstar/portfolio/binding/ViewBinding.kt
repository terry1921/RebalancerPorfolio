package dev.rockstar.portfolio.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import com.skydoves.whatif.whatIfNotNullOrEmpty

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text.whatIfNotNullOrEmpty { message ->
            Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("snack")
    fun bindSnack(view: View, text: Int) {
        if (text != -1) {
            Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).show()
        }
    }
    
    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

}