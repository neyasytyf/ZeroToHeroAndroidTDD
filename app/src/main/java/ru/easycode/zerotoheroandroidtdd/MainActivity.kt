package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.actionButton)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val titleTextView: TextView = findViewById(R.id.titleTextView)

        button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            button.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    progressBar.visibility = View.GONE
                    button.isEnabled = true
                    titleTextView.visibility = View.VISIBLE
                }, 3000
            )
        }
    }
}
