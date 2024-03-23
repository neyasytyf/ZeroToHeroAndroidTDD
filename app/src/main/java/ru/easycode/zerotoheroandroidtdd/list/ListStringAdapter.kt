package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemRecycledViewBinding

class ListStringAdapter : RecyclerView.Adapter<StringViewHolder>() {

    private val listString = ArrayList<CharSequence>()

    fun updateList(newList: List<CharSequence>) {
        val diffListString = DiffListString(listString, newList)
        val diffResult = DiffUtil.calculateDiff(diffListString)
        listString.clear()
        listString.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        return StringViewHolder(ItemRecycledViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listString.size

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(listString[position])
    }
}

class StringViewHolder(private val binding: ItemRecycledViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: CharSequence) {
        binding.elementTextView.text = text
    }
}