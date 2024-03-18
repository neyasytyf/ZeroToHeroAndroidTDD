package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(progressBar: ProgressBar, titleTextView: TextView, actionButton: Button)

    data object ShowProgress : UiState {
        override fun apply(
            progressBar: ProgressBar,
            titleTextView: TextView,
            actionButton: Button
        ) {
            progressBar.visibility = View.VISIBLE
            titleTextView.visibility = View.GONE
            actionButton.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun apply(
            progressBar: ProgressBar,
            titleTextView: TextView,
            actionButton: Button
        ) {
            progressBar.visibility = View.GONE
            titleTextView.visibility = View.VISIBLE
            titleTextView.text = text
            actionButton.isEnabled = true
        }
    }
}