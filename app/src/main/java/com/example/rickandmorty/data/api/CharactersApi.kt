package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.model.APIResult
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(): Response<APIResult>
}