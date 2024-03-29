package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface Update {
        fun update(value: List<String>)
    }

    interface Read {
        fun liveData(): LiveData<List<String>>
    }

    interface Add {
        fun add(value: String)
    }

    interface Mutable : Read, Update

    interface All : Mutable, Add

    class Base(private val liveData: MutableLiveData<List<String>>) : All {

        override fun liveData(): LiveData<List<String>> {
            return liveData
        }

        override fun update(value: List<String>) {
            liveData.value = value
        }

        override fun add(value: String) {
            val current = liveData.value?.toMutableList() ?: mutableListOf()
            current.add(value)
            update(current)
        }
    }
}