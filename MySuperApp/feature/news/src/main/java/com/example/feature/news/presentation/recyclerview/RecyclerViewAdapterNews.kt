package com.example.feature.news.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.feature.news.databinding.RvItemNewsBinding
import com.example.feature.news.data.model.NewsResultModel

class RecyclerViewAdapterNews(var mContext : Context,
                              var list : ArrayList<NewsResultModel>,
                              var listenerPagination : com.example.feature.news.listener.PaginationListener,
                              var listenerClickItem : com.example.feature.news.listener.ClickListenerItem
)
    : RecyclerView.Adapter<RecyclerViewAdapterNews.NewsHolder>(){

    class NewsHolder(var binding : RvItemNewsBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = RvItemNewsBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return NewsHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.binding.textViewNewsItem.text = list.get(position).name
        Glide.with(mContext).load(list.get(position).image).into(holder.binding.imageViewNewsItem)

        if (list.size-3 == position){
            listenerPagination.getPage()
        }

        holder.binding.cardViewNewsItem.setOnClickListener {
            listenerClickItem.clickItem(position)
        }
    }

    fun newList(newList : ArrayList<NewsResultModel>){
        list.addAll(newList)
        notifyDataSetChanged()
    }
}