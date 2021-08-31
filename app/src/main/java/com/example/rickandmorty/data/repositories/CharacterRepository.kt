package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.entity.Character
import com.example.rickandmorty.entity.Result

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<Character>>
}