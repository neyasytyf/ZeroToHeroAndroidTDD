package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val actionButton: Button = findViewById(R.id.actionButton)

        viewModel = (application as ProvideViewModule).provideViewModel()

        viewModel.liveData().observe(this) {
            it.apply(progressBar, titleTextView, actionButton)
        }

        actionButton.setOnClickListener {
            viewModel.load()
        }
    }
}