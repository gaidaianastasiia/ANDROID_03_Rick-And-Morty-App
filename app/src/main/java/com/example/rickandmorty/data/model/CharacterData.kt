package com.example.rickandmorty.data.model

import com.example.rickandmorty.entity.Gender
import com.example.rickandmorty.entity.Species
import com.example.rickandmorty.entity.Status

data class CharacterData(
    val id: Long,
    val name: String,
    val status: Status,
    val species: Species,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
