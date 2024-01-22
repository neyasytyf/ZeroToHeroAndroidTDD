package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DeleteViewModel(
    deleteLiveDataWrapper: ListLiveDataWrapper.All,
    repository: Repository.Delete,
    clear: ClearViewModel,
    dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
    dispatcherMain: CoroutineDispatcher = Dispatchers.Unconfined
) : ViewModel() {

    private val _livadata = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = _livadata

    fun init(itemId: Long) {


    }

    fun delete(itemId: Long) {

    }

    fun comeback() {
        TODO("Not yet implemented")
    }


}