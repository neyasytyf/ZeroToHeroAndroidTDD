package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var counter = Count.Base(2, 4)
    private var uiState: UiState = UiState.Base("0")
    private lateinit var button: Button
    private lateinit var count: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.incrementButton)
        count = findViewById(R.id.countTextView)
        button.setOnClickListener {
            uiState = counter.increment(count.text.toString())
            uiState.apply(count, button)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable(KEY) as UiState
        uiState.apply(count, button)
    }

    companion object {
        private const val KEY = "key"
    }

}