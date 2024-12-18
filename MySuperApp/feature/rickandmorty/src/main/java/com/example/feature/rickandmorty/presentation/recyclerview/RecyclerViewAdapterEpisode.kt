package com.example.feature.rickandmorty.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.databinding.RvItemRamEpisodeBinding
import com.example.feature.rickandmorty.listener.ClickListener
import com.example.feature.rickandmorty.listener.PaginationListener
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel

class RecyclerViewAdapterEpisode(var mContext : Context,
                                 var list : ArrayList<EpisodeResultModel>,
                                 var listener : PaginationListener,
                                 var click : ClickListener
)
    : RecyclerView.Adapter<RecyclerViewAdapterEpisode.EpisodeHolder>(){

    class EpisodeHolder(var binding : RvItemRamEpisodeBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val binding = RvItemRamEpisodeBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return EpisodeHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.binding.textViewEpisode.text = list.get(position).name
        holder.binding.imageViewEpisode.setImageResource(R.drawable.ram_episode)
        holder.binding.cardViewEpisode.setOnClickListener {
            click.click(list.get(position).id)
        }

        if (list.size-5 == position){
            listener.getPage()
        }
    }

    fun newList(newList : ArrayList<EpisodeResultModel>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

}