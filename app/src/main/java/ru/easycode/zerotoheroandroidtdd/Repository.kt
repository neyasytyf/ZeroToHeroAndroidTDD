package ru.easycode.zerotoheroandroidtdd

interface Repository {

    interface Add {
        fun add(value: String): Long
    }

    interface Delete {

        fun item(id: Long): Item

        fun delete(id: Long)
    }

    interface Read {
        fun list(): List<Item>
    }

    interface All : Read, Add, Delete

    class Base(dataSource: ItemsDao, now: Now) : All {
        override fun list(): List<Item> {
            return listOf()
        }

        override fun add(value: String): Long {
            return 1L
        }

        override fun item(id: Long): Item {
            return Item(1L, "sddd")
        }

        override fun delete(id: Long) {

        }
    }

}