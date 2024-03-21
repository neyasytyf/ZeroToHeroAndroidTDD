package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemRvBinding

class ListTextAdapter : RecyclerView.Adapter<TextViewHolder>() {

    private val listString = mutableListOf<CharSequence>()

    fun addListText(list: List<CharSequence>) {

        val diffUtil = TextDiffUtil(listString, list)
        val calculate = DiffUtil.calculateDiff(diffUtil)
        listString.clear()
        listString.addAll(list)
        calculate.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listString.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(listString[position])
    }
}

class TextViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: CharSequence) {
        binding.elementTextView.text = text
    }
}

