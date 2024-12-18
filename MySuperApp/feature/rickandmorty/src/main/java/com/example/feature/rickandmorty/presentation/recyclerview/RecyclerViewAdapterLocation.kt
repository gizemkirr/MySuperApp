package com.example.feature.rickandmorty.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.databinding.RvItemRamLocationBinding
import com.example.feature.rickandmorty.listener.ClickListener
import com.example.feature.rickandmorty.listener.PaginationListener
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel

class RecyclerViewAdapterLocation(var mContext : Context,
                                  var list : ArrayList<LocationResultModel>,
                                  var listener : PaginationListener,
                                  var click : ClickListener
) : RecyclerView.Adapter<RecyclerViewAdapterLocation.LocationHolder>(){

    class LocationHolder(var binding : RvItemRamLocationBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val binding = RvItemRamLocationBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return LocationHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.binding.textViewLocation.text = list.get(position).name
        holder.binding.imageViewLocation.setImageResource(R.drawable.ram_collapsing_toolbar)
        holder.binding.cardViewLocation.setOnClickListener {
            click.click(list.get(position).id)
        }

        if (list.size-5 == position){
            listener.getPage()
        }
    }

    fun newList(newList : ArrayList<LocationResultModel>){
        list.addAll(newList)
        notifyDataSetChanged()
    }
}