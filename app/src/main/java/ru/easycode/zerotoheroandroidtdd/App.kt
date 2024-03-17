package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App : Application(), ProvideViewModule {

    private lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val singleLiveEvent = SingleLiveEvent<UiState>()
        val liveDataWrapper = LiveDataWrapper.Base(singleLiveEvent)
        val repository: Repository = Repository.Base()
        viewModel = MainViewModel(liveDataWrapper, repository)
    }

    override fun provideViewModule(): MainViewModel {
        return viewModel
    }
}