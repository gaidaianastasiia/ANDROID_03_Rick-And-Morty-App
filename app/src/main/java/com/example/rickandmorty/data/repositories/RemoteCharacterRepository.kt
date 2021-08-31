package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.api.CharactersApi
import com.example.rickandmorty.entity.Character
import javax.inject.Inject
import com.example.rickandmorty.entity.Result
import com.example.rickandmorty.utils.toCharacters
import java.io.IOException

class RemoteCharacterRepository @Inject constructor(
    private val charactersAPI: CharactersApi
) : CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<Character>> {
        val response = charactersAPI.getCharacters()
        return if (response.isSuccessful) {
            val characters = response.body()?.results?.map { it.toCharacters() }
            Result.Success(characters ?: emptyList())
        } else {
            Result.Error(IOException())
        }
    }
}