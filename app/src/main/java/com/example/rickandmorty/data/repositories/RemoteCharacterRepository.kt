package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.`interface`.CharactersApi
import com.example.rickandmorty.data.model.Character
import javax.inject.Inject
import com.example.rickandmorty.entity.Result
import java.io.IOException

class RemoteCharacterRepository @Inject constructor(
    private val charactersAPI: CharactersApi
) : CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<Character>> {
        val response = charactersAPI.getCharacters()
        return if (response.isSuccessful) {
            Result.Success(response.body()?.results!!)
        } else {
            Result.Error(IOException())
        }
    }
}