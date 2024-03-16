package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

interface State : Serializable {

    fun apply(linearLayout: LinearLayout, title: TextView, button: Button)

    object Initial : State {
        override fun apply(linearLayout: LinearLayout, title: TextView, button: Button) = Unit
    }

    object Removed : State {
        override fun apply(linearLayout: LinearLayout, title: TextView, button: Button) {
            linearLayout.removeView(title)
            button.isEnabled = false
        }
    }
}