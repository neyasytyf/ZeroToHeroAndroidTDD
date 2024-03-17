package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App : Application(), ProvideViewModel {

    private lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val liveDataWrapper: LiveDataWrapper =
            LiveDataWrapper.Base()
        val repository: Repository = Repository.Base()
        viewModel = MainViewModel(liveDataWrapper, repository)
    }

    override fun provideViewModel(): MainViewModel {
        return viewModel
    }
}