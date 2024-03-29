package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun liveData() = liveDataWrapper.liveData()

    fun init() {
        scope.launch(dispatcher) {
            val result = repository.list()
            withContext(dispatcherMain) {
                liveDataWrapper.update(result)
            }
        }
    }
}