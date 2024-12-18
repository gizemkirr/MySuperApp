package com.example.feature.rickandmorty

import android.content.Context
import androidx.room.Room
import com.example.common.BaseDataStore
import com.example.common.BaseRetrofitFactory
import com.example.feature.rickandmorty.data.repo.database.CharacterDataBase
import com.example.feature.rickandmorty.data.repo.database.CharacterDatabaseDataSource
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteDao
import com.example.feature.rickandmorty.data.repo.database.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseRetrofitFactory() : BaseRetrofitFactory {
        return BaseRetrofitFactory()
    }

    @Provides
    fun provideCharacterDatabaseDataSource(cdao : CharacterFavoriteDao) : CharacterDatabaseDataSource {
        return CharacterDatabaseDataSource(cdao)
    }

    @Provides
    fun provideCharacterRepository(cds : CharacterDatabaseDataSource) : CharacterRepository {
        return CharacterRepository(cds)
    }

    @Provides
    fun provideCharacterFavoriteDao(@ApplicationContext context: Context) : CharacterFavoriteDao {
        val db = Room.databaseBuilder(context, CharacterDataBase::class.java,"characterfavorite.sqlite")
            .allowMainThreadQueries()
            .build()
        return db.getCharacterDao()
    }

    @Provides
    fun provideBaseDataStore(@ApplicationContext context: Context) : BaseDataStore {
        return BaseDataStore(context)
    }
}