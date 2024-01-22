package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AddViewModel(
    repository: Repository.Add,
    liveDataWrapper: ListLiveDataWrapper.Add,
    clear: ClearViewModel,
    dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
    dispatcherMain: CoroutineDispatcher = Dispatchers.Unconfined
) : ViewModel() {
    fun comeback() {
        TODO("Not yet implemented")
    }

    fun add(value: String) {

    }
}