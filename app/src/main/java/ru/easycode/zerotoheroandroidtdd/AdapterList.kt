package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextViewBinding

class AdapterList : RecyclerView.Adapter<TextViewHolder>() {

    private val list = mutableListOf<String>()

    fun update(newList: List<String>) {
        val diffUtil = DiffUtilList(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(ItemTextViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

class TextViewHolder(private val binding: ItemTextViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(name: String) {
        binding.elementTextView.text = name
    }
}