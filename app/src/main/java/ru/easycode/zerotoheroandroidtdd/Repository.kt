package ru.easycode.zerotoheroandroidtdd

interface Repository {

    interface Read : Repository {
        fun list(): List<String>
    }

    interface Add : Repository {
        fun add(value: String)
    }

    interface Mutable : Add, Read

    class Base(private val dataSource: ItemsDao, private val now: Now) : Mutable {
        override fun add(value: String) {
            dataSource.add(ItemCache(now.nowMillis(), value))
        }

        override fun list(): List<String> {
            return dataSource.list().map { it.text }
        }
    }

}