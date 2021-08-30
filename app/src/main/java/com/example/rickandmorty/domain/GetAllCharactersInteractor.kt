package com.example.rickandmorty.domain

import com.example.rickandmorty.data.repositories.CharacterRepository
import javax.inject.Inject

class GetAllCharactersInteractor @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke() = repository.getAllCharacters()
}