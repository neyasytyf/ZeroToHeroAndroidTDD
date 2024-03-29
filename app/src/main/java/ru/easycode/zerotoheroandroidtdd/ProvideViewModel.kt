package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clazz: Class<T>): T

    class Base(core: Core, private val clearViewModel: ClearViewModel) :
        ProvideViewModel {

        private val repository = Repository.Base(core.dao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base(MutableLiveData())

        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return when (clazz) {
                MainViewModel::class.java -> {
                    val mainViewModel = MainViewModel(repository, liveDataWrapper)
                    mainViewModel
                }

                AddViewModel::class.java -> {
                    val addViewModel = AddViewModel(
                        repository,
                        liveDataWrapper as ListLiveDataWrapper.Add,
                        clearViewModel
                    )
                    addViewModel
                }

                else -> {
                    throw IllegalStateException("unknown class $clazz")
                }
            } as T
        }
    }
}