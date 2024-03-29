package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapterList: AdapterList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as ProvideViewModel).viewModel(MainViewModel::class.java)
        viewModel.init()

        adapterList = AdapterList()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterList

        binding.addButton.setOnClickListener {
            val modalBottomSheet = AddBottomSheet()
            modalBottomSheet.show(supportFragmentManager, AddBottomSheet.TAG)
        }

        viewModel.liveData().observe(this) {
            adapterList.update(it)
        }
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return (application as ProvideViewModel).viewModel(clazz)
    }
}