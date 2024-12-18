package com.example.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.mysuperapp : DataStore<Preferences> by preferencesDataStore("mysuperappstore")

class BaseDataStore(var context : Context) {

    companion object{
        val INTRODUCTION = booleanPreferencesKey("introduction")
    }

    suspend fun saveIntroduction(){
        context.mysuperapp.edit {
            it[INTRODUCTION] = true
        }
    }

    suspend fun getIntroduction() : Boolean? {
        val introduction = context.mysuperapp.data.first()
        return introduction[INTRODUCTION]
    }
}