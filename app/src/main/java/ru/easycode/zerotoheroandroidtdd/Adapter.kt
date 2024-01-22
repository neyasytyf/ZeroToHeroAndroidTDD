package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding

class Adapter(private val deleteItem: DeleteItemUi) : RecyclerView.Adapter<ItemViewHolder>() {

    private val list = mutableListOf<ItemUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(deleteItem, ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(newList: List<ItemUi>) {
        val diff = Diff(list, newList)
        val result = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

}

class ItemViewHolder(private val deleteItem: DeleteItemUi, private val binding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(itemUi: ItemUi) {
        itemUi.show(binding.elementTextView)
        itemView.setOnClickListener {
            itemUi.delete(deleteItem)
        }
    }
}

private class Diff(
    private val old: List<ItemUi>,
    private val new: List<ItemUi>
) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].areItemsSame(new[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

}