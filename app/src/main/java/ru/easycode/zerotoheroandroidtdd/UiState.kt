package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(count: TextView, increment: Button, decrement: Button)

    data class Min(private val text: String) : UiState {
        override fun apply(count: TextView, increment: Button, decrement: Button) {
            count.text = text
            increment.isEnabled = true
            decrement.isEnabled = false
        }

    }

    data class Max(private val text: String) : UiState {
        override fun apply(count: TextView, increment: Button, decrement: Button) {
            count.text = text
            increment.isEnabled = false
            decrement.isEnabled = true
        }

    }

    data class Base(private val text: String) : UiState {
        override fun apply(count: TextView, increment: Button, decrement: Button) {
            count.text = text
            increment.isEnabled = true
            decrement.isEnabled = true
        }
    }
}