package com.example.rickandmorty.data.common

import com.example.rickandmorty.data.`interface`.CharactersApi
import com.example.rickandmorty.data.retrofit.RetrofitClient

object Common {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    val charactersApi: CharactersApi
        get() = RetrofitClient.getClient(BASE_URL).create(CharactersApi::class.java)
}