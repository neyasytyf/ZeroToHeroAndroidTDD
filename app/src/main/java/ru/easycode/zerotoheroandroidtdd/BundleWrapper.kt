package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import java.util.ArrayList

interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, list)
        }

        override fun restore(): List<CharSequence> {
            val arrayList = bundle.getCharSequenceArrayList(KEY)
            return arrayList?.toList() ?: listOf()
        }

        companion object {
            private const val KEY = "key_live_data"
        }
    }
}