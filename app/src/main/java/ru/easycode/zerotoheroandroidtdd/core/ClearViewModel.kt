package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {

    fun clear(viewModelClass: Class<out ViewModel>)

    class Base(private val viewModelFactory: ViewModelFactory) : ClearViewModel {
        override fun clear(viewModelClass: Class<out ViewModel>) {
            viewModelFactory.clear(viewModelClass)
        }
    }
}