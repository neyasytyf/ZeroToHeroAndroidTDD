package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room

class Core(private val context: Context) {

    private val dataBase by lazy {
        Room.databaseBuilder(context, ItemsDataBase::class.java, "todo")
            .build()
    }

    fun dao() = dataBase.itemsDao()
}