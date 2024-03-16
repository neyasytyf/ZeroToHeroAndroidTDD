package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(count: TextView, button: Button)

    data class Base(private val text: String) : UiState {
        override fun apply(count: TextView, button: Button) {
            count.text = text
            button.isEnabled = true
        }
    }

    data class Max(private val text: String) : UiState {
        override fun apply(count: TextView, button: Button) {
            count.text = text
            button.isEnabled = false
        }
    }

}