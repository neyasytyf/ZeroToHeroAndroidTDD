package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:

            override    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java)

        val adapter = Adapter(object : DeleteItemUi {
            override fun delete(itemId: Long) {
                DeleteBottomSheetFragment.newInstance(itemId)
                    .show(supportFragmentManager, "DeleteBottomSheetFragment")
            }
        })

        binding.recyclerView.adapter = adapter

        viewModel.livedata().observe(this) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "AddBottomSheetFragment")
        }

        viewModel.init()
    }
}