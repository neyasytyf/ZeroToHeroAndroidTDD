package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var provideViewModel: ProvideViewModel
    private val viewModelList = HashMap<Class<out ViewModel>, ViewModel>()
    private val clearViewModel: ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            viewModelList.remove(clasz)
        }
    }

    override fun onCreate() {
        super.onCreate()

        val core = Core(this)
        provideViewModel = ProvideViewModel.Base(core, clearViewModel)
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        if (viewModelList[clazz] == null) {
            viewModelList[clazz] = provideViewModel.viewModel(clazz)
        }
        return viewModelList[clazz] as T
    }
}

