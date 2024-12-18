package com.example.feature.weathers.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.feature.weathers.databinding.RvItemWeathersBinding
import com.example.feature.weathers.data.model.WeatherResultModel

class RecyclerViewAdapterWeathers (var mContext : Context, var list : ArrayList<WeatherResultModel>)
    : RecyclerView.Adapter<RecyclerViewAdapterWeathers.WeathersHolder>(){

    class WeathersHolder(var binding : RvItemWeathersBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeathersHolder {
        val binding = RvItemWeathersBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return WeathersHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WeathersHolder, position: Int) {
        holder.binding.textViewWeathersDate.text = "Tarih : " + list.get(position).date
        holder.binding.textViewWeathersDay.text = "Gün : " + list.get(position).day
        Glide.with(mContext).load(list.get(position).icon).into(holder.binding.imageViewWeathersIcon)
        holder.binding.textViewWeathersDescription.text = "Açıklama : " + list.get(position).description
        holder.binding.textViewWeathersStatus.text = "Durum : " + list.get(position).status
        holder.binding.textViewWeathersDegree.text = "Derece : " + list.get(position).degree
        holder.binding.textViewWeathersMin.text = "Min : " + list.get(position).min
        holder.binding.textViewWeathersMax.text = "Max : " + list.get(position).max
        holder.binding.textViewWeathersNight.text = "Gece : " + list.get(position).night
        holder.binding.textViewWeathersHumidity.text = "Nem Oranı : " +list.get(position).humidity
    }
}