package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel
    private val store = HashMap<Class<out ViewModel>, ViewModel?>()

    private val clear: ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            store[clasz] = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)
        factory = ProvideViewModel.Base(core, clear)
    }

    override fun <T : ViewModel> viewModule(clasz: Class<T>): T {
        if (store[clasz] == null) {
            store[clasz] = factory.viewModule(clasz)
        }
        return store[clasz] as T
    }


}