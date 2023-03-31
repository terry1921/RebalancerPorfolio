package dev.rockstar.portfolio.utils

import android.widget.SeekBar

inline fun SeekBar.addProgressChangedListener(
    crossinline onStartTrackingTouch: (SeekBar) -> Unit = { _ -> },
    crossinline onStopTrackingTouch: (SeekBar) -> Unit = { _ -> },
    crossinline onProgressChanged: (SeekBar, Int, Boolean) -> Unit = { _, _, _ -> }
): SeekBar.OnSeekBarChangeListener {
    val listener = object : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(seekBar: SeekBar) {
            onStartTrackingTouch.invoke(seekBar)
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            onStopTrackingTouch.invoke(seekBar)
        }

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            onProgressChanged.invoke(seekBar, progress, fromUser)
        }
    }
    setOnSeekBarChangeListener(listener)

    return listener
}