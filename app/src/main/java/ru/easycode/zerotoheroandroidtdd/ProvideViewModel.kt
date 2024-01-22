package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModule(clasz: Class<T>): T

    class Base(core: Core, private val clear: ClearViewModel) :
        ProvideViewModel {

        private val repository = Repository.Base(core.dao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()


        override fun <T : ViewModel> viewModule(clasz: Class<T>): T = when (clasz) {
            MainViewModel::class.java -> MainViewModel(repository, liveDataWrapper)
            AddViewModel::class.java -> AddViewModel(repository, liveDataWrapper, clear)
            else -> DeleteViewModel(liveDataWrapper, repository, clear)
        } as T
    }
}