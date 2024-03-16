package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var ll: LinearLayout
    private lateinit var title: TextView
    private var state: State = State.Initial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll = findViewById(R.id.rootLayout)
        title = findViewById(R.id.titleTextView)
        val button: Button = findViewById(R.id.removeButton)

        button.setOnClickListener {
            state = State.Removed
            state.apply(ll, title)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getSerializable(KEY) as State
        state.apply(ll, title)
    }

    companion object {
        private const val KEY = "key"
    }
}