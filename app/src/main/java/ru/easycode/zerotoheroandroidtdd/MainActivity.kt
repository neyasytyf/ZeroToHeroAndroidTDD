package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var state: State = State.Initial
    private lateinit var ll: LinearLayout
    private lateinit var button: Button
    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll = findViewById(R.id.rootLayout)
        button = findViewById(R.id.removeButton)
        title = findViewById(R.id.titleTextView)

        button.setOnClickListener {
            state = State.Removed
            state.apply(ll, title, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getSerializable(KEY) as State
        state.apply(ll, title, button)
    }

    companion object {
        private const val KEY = "key"
    }
}