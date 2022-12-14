package co.innovatespace.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.innovatespace.ui.databinding.NewsItemBinding
import co.innovatespace.ui.presentation.UINews

class NewsAdapter: PagingDataAdapter<UINews, NewsAdapter.NewsViewHolder>(ITEM_COMPARATOR) {

    inner class NewsViewHolder(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: UINews?) {
            binding.source.text = item?.description
            binding.date.text = item?.pubDate
            binding.newsThumbnail.setImage(item?.image.orEmpty())
            binding.headline.text = item?.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UINews>(){
    override fun areItemsTheSame(oldItem: UINews, newItem: UINews): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UINews, newItem: UINews): Boolean = oldItem == newItem

}