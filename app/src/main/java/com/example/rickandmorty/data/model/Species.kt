package com.example.rickandmorty.data.model

enum class Species(val value: String) {
    Alien("Alien"),
    Human("Human");

    companion object {
        public fun fromValue(value: String): Species = when (value) {
            "Alien" -> Alien
            "Human" -> Human
            else    -> throw IllegalArgumentException()
        }
    }
}