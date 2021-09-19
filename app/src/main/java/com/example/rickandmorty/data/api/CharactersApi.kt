package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.model.APIResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber: Long): Response<APIResult>
}