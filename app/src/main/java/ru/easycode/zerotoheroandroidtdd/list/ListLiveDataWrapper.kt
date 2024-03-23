package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<CharSequence>>

    interface Update : LiveDataWrapper.Update<List<CharSequence>>

    interface Mutable : Read, Update {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add {
        fun add(source: CharSequence)
    }

    interface All : Mutable, Add

    class Base(liveData: MutableLiveData<List<CharSequence>>) :
        LiveDataWrapper.Abstract<List<CharSequence>>(liveData), All {

        override fun add(source: CharSequence) {
            val list: MutableList<CharSequence> = liveData.value?.toMutableList() ?: ArrayList()
            list.add(source)
            update(list)
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let {
                bundleWrapper.save(ArrayList(it))
            }
        }
    }
}