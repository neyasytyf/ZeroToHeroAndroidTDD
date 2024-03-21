package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>
    fun add(new: CharSequence)
    fun save(bundle: BundleWrapper.Save)
    fun update(list: List<CharSequence>)

    class Base(private val liveData: MutableLiveData<List<CharSequence>>) : ListLiveDataWrapper {
        override fun liveData(): LiveData<List<CharSequence>> {
            return liveData
        }

        override fun add(new: CharSequence) {
            val newList = mutableListOf<CharSequence>()
            liveData.value?.let {
                newList.addAll(it)
            }
            newList.add(new)
            update(newList)
        }

        override fun save(bundle: BundleWrapper.Save) {
            liveData.value?.let {
                bundle.save(ArrayList(it))
            }
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = list
        }
    }
}