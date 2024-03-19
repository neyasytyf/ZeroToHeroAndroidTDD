package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listContent = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            actionButton.setOnClickListener {
                addTextView(inputEditText.text.toString())
                inputEditText.text?.clear()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY, listContent)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val listSave = savedInstanceState.getStringArrayList(KEY)
        listSave?.forEach {
            addTextView(it as String)
        }
    }

    private fun addTextView(textContent: String) {
        val textView = TextView(this@MainActivity)
        textView.text = textContent
        listContent.add(textContent)
        binding.contentLayout.addView(textView)
    }

    companion object {
        private const val KEY = "key"
    }
}