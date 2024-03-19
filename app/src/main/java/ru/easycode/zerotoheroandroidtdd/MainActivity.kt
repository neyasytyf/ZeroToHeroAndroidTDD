package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            actionButton.setOnClickListener {
                titleTextView.text = inputEditText.text
                inputEditText.text?.clear()
            }
            inputEditText.addTextChangedListener {
                inputEditText.text?.let {
                    actionButton.isEnabled = it.length > 2
                }
            }
        }
    }
}