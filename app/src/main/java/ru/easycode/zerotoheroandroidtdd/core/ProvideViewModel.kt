package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(private val clearViewModel: ClearViewModel) : ProvideViewModel {

        private val navigation = Navigation.Base(SingleLiveEvent())
        private val liveDataWrapper = ListLiveDataWrapper.Base(SingleLiveEvent())

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> {
                    MainViewModel(navigation)
                }

                ListViewModel::class.java -> {
                    ListViewModel(liveDataWrapper, navigation)
                }

                CreateViewModel::class.java -> {
                    CreateViewModel(liveDataWrapper, navigation, clearViewModel)
                }

                else -> {
                    throw IllegalStateException("unknown view model class $viewModelClass")
                }
            } as T
        }
    }
}