package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.api.CharactersApi
import com.example.rickandmorty.data.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideCharacterAPI(): CharactersApi =
        RetrofitClient.getClient(BASE_URL).create(CharactersApi::class.java)
}