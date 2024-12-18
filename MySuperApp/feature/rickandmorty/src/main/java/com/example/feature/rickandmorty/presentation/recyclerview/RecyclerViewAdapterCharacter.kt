package com.example.feature.rickandmorty.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.common.safeButtonClickListener
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteModel
import com.example.feature.rickandmorty.databinding.RvItemRamCharacterBinding
import com.example.feature.rickandmorty.listener.ClickFavoriteListener
import com.example.feature.rickandmorty.listener.ClickListener
import com.example.feature.rickandmorty.listener.PaginationListener
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel

class RecyclerViewAdapterCharacter(var mContext : Context,
                                   var list : ArrayList<CharacterResultModel>,
                                   var favoriteList : ArrayList<CharacterFavoriteModel>,
                                   var listener : PaginationListener,
                                   var click : ClickListener,
                                   var clickFavorite : ClickFavoriteListener
)
    : RecyclerView.Adapter<RecyclerViewAdapterCharacter.CharacterHolder>() {

    class CharacterHolder(var binding : RvItemRamCharacterBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = RvItemRamCharacterBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CharacterHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.binding.textViewCharacter.text = list.get(position).name
        Glide.with(mContext).load(list.get(position).image).into(holder.binding.imageViewCharacter)
        holder.binding.cardViewCharacter.setOnClickListener {
            click.click(list.get(position).id)
        }

        if (list.size-5 == position){
            listener.getPage()
        }

        var favoriteBool = false
        val model = favoriteList.find { it.id == list.get(position).id }
        if (model != null){
            favoriteBool = true
        }
        if (favoriteBool) {
            Glide.with(mContext).load(R.drawable.ic_favorite).into(holder.binding.imageViewAddFavCharacter)
        } else {
            Glide.with(mContext).load(R.drawable.ic_favorite_empty).into(holder.binding.imageViewAddFavCharacter)
        }

        holder.binding.imageViewAddFavCharacter.safeButtonClickListener {
            val favoriteModel = favoriteList.find { favorite ->
                list.get(position).id == favorite.id
            }
            if (favoriteModel != null){
                Glide.with(mContext).load(R.drawable.ic_favorite_empty).into(holder.binding.imageViewAddFavCharacter)
            }else{
                Glide.with(mContext).load(R.drawable.ic_favorite).into(holder.binding.imageViewAddFavCharacter)
            }
            clickFavorite.getFavorite(list.get(position).id,favoriteModel == null)
        }

    }

    fun newList(newList : ArrayList<CharacterResultModel>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun newFavoriteList(newList : List<CharacterFavoriteModel>){
        favoriteList.clear()
        favoriteList.addAll(newList)
        notifyDataSetChanged()
    }
}