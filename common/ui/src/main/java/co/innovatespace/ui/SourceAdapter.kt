package co.innovatespace.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.innovatespace.ui.databinding.NewsSourceBinding
import co.innovatespace.ui.presentation.UISource

class SourceAdapter: PagingDataAdapter<UISource, SourceAdapter.SourceViewHolder>(ITEM_COMPARATOR) {
    inner class SourceViewHolder(private val binding: NewsSourceBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: UISource?) {
            binding.source.text = item?.name
            binding.description.text = item?.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val binding = NewsSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  SourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val sourceItem = getItem(position)
        holder.bind(sourceItem)
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UISource>(){
    override fun areItemsTheSame(oldItem: UISource, newItem: UISource): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UISource, newItem: UISource): Boolean = oldItem == newItem

}