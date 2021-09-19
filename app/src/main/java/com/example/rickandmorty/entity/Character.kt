package com.example.rickandmorty.entity

data class Character(
    val id: Long,
    val image: String,
    val name: String,
    val status: Status,
    val species: Species?,
    val gender: Gender?
)
