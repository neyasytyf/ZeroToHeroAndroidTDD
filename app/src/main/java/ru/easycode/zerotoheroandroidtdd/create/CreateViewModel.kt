package ru.easycode.zerotoheroandroidtdd.create

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.main.Screen

class CreateViewModel(
    private val addLiveDataWrapper: ListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel
) : ViewModel() {

    fun comeback() {
        navigation.update(Screen.Pop)
        clearViewModel.clear(CreateViewModel::class.java)
    }

    fun add(text: String) {
        addLiveDataWrapper.add(text)
        comeback()
    }
}