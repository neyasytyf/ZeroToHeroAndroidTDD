package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.RvElementBinding
import kotlin.collections.ArrayList

class AdapterText : RecyclerView.Adapter<AdapterText.ViewHolderText>() {

    private val listString: MutableList<String> = mutableListOf()

    fun addToList(text: String) {
        listString.add(text)
        notifyDataSetChanged()
    }

    fun save(bundle: Bundle) {
        bundle.putStringArrayList(KEY, ArrayList(listString))
    }

    fun restore(bundle: Bundle) {
        val listRestore = bundle.getStringArrayList(KEY)
        listRestore?.let {
            listString.addAll(listRestore)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderText {
        val binding = RvElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderText(binding)
    }

    override fun getItemCount(): Int {
        return listString.size
    }

    override fun onBindViewHolder(holder: ViewHolderText, position: Int) {
        holder.bind(listString[position])
    }

    class ViewHolderText(private val binding: RvElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.elementTextView.text = text
        }
    }

    companion object {
        private const val KEY = "adapter_text_key"
    }
}

