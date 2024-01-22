package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    repository: Repository.Read,
    liveDataWrapper: ListLiveDataWrapper.All,
    dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
    dispatcherMain: CoroutineDispatcher = Dispatchers.Unconfined

) {
    fun init() {

    }
}