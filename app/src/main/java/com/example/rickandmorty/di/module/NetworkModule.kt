package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.`interface`.CharactersApi
import com.example.rickandmorty.data.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
class NetworkModule {
    @Provides
    fun provideCharacterAPI(): CharactersApi =
        RetrofitClient.getClient(BASE_URL).create(CharactersApi::class.java)
}