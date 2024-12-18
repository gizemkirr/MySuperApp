package com.example.feature.rickandmorty.data.repo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharacterFavoriteModel (

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "image")
    var image : String,

)