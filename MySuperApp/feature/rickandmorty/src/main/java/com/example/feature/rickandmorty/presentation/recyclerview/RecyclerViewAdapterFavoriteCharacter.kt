package com.example.feature.rickandmorty.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteModel
import com.example.feature.rickandmorty.databinding.RvItemRamFavoriteBinding

class RecyclerViewAdapterFavoriteCharacter(var mContext : Context,
                                           var list : List<CharacterFavoriteModel>)
    : RecyclerView.Adapter<RecyclerViewAdapterFavoriteCharacter.FavoriteHolder>() {

    class FavoriteHolder(var binding : RvItemRamFavoriteBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding = RvItemRamFavoriteBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return FavoriteHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.binding.textViewCharacterFavorite.text = list.get(position).name
        Glide.with(mContext).load(list.get(position).image).into(holder.binding.imageViewCharacterFavorite)
    }
}