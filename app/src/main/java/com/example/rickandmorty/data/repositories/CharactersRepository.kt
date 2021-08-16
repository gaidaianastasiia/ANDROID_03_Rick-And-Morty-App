package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.model.APIResult

interface CharactersRepository {
    suspend fun getAPIResult(): APIResult
}