package ru.easycode.zerotoheroandroidtdd.list

import androidx.recyclerview.widget.DiffUtil

class DiffListString(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}