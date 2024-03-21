package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.MutableLiveData

class App : Application(), ProvideViewModel {

    private lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val liveData: MutableLiveData<List<CharSequence>> = SingleLiveEvent()
        val listLiveDataWrapper: ListLiveDataWrapper = ListLiveDataWrapper.Base(liveData)
        viewModel = MainViewModel(listLiveDataWrapper)
    }

    override fun provideViewModel(): MainViewModel {
        return viewModel
    }
}