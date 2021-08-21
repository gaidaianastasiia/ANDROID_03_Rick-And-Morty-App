package com.example.rickandmorty.domain

import com.example.rickandmorty.data.repositories.RemoteCharacterRepository
import javax.inject.Inject

class GetAllCharactersInteractor @Inject constructor(
    private val repository: RemoteCharacterRepository
) {
    suspend operator fun invoke() = repository.getAllCharacters()
}