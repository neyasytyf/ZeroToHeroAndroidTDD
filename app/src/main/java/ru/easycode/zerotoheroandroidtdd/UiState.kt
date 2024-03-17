package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

interface UiState {

    fun apply(progressBar: ProgressBar, titleTextView: TextView, actionButton: Button)

    data object ShowProgress : UiState {
        override fun apply(
            progressBar: ProgressBar,
            titleTextView: TextView,
            actionButton: Button
        ) {
            progressBar.visibility = View.VISIBLE
            titleTextView.visibility = View.INVISIBLE
            actionButton.isEnabled = false
        }
    }

    data object ShowData : UiState {
        override fun apply(
            progressBar: ProgressBar,
            titleTextView: TextView,
            actionButton: Button
        ) {
            progressBar.visibility = View.GONE
            titleTextView.visibility = View.VISIBLE
            actionButton.isEnabled = true
        }
    }
}