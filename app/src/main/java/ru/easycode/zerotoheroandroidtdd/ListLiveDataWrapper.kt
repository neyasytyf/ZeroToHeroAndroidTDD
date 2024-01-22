package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: ItemUi)
    }

    interface All {
        fun update(value: List<ItemUi>)

        fun liveData(): LiveData<List<ItemUi>>

        fun add(value: ItemUi)

        fun delete(item: ItemUi)
    }

    class Base : All {
        override fun update(value: List<ItemUi>) {
            TODO("Not yet implemented")
        }

        override fun liveData(): LiveData<List<ItemUi>> {
            TODO("Not yet implemented")
        }

        override fun add(value: ItemUi) {
            TODO("Not yet implemented")
        }

        override fun delete(item: ItemUi) {
            TODO("Not yet implemented")
        }
    }

}