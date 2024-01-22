package ru.easycode.zerotoheroandroidtdd

interface ItemsDao {

    fun list(): List<ItemCache>

    fun add(item: ItemCache)

    fun item(id: Long): ItemCache

    fun delete(id: Long)

}