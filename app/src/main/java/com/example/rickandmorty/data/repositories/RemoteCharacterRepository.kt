package com.example.rickandmorty.data.repositories

import android.util.Log
import com.example.rickandmorty.data.api.CharactersApi
import com.example.rickandmorty.entity.Character
import javax.inject.Inject
import com.example.rickandmorty.entity.Result
import com.example.rickandmorty.utils.toCharacters
import java.io.IOException

class RemoteCharacterRepository @Inject constructor(
    private val charactersAPI: CharactersApi
) : CharacterRepository {
    private var maxPageNumber: Long? = null

    override suspend fun getAllCharacters(page: Long): Result<List<Character>> {
        if (maxPageNumber != null && page > requireNotNull(maxPageNumber)) {
            return Result.Success(emptyList())
        }

        val response = charactersAPI.getCharacters(page)

        return if (response.isSuccessful) {
            val body = response.body()
            maxPageNumber = body?.info?.pages
            val characters = response.body()?.results?.map { it.toCharacters() }

            Result.Success(characters ?: emptyList())
        } else {
            Result.Error(IOException())
        }
    }
}