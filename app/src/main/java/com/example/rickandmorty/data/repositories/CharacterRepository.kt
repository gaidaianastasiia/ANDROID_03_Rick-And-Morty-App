package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.entity.Result

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<Character>>
}