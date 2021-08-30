package com.example.rickandmorty.entity

import com.example.rickandmorty.R
import com.google.gson.annotations.SerializedName

enum class Species(val stringRes: Int) {
    @SerializedName("Alien")
    ALIEN(R.string.character_alien_species),
    @SerializedName("Human")
    HUMAN(R.string.character_human_species);

    companion object {
        public fun fromValue(value: String): Species = when (value) {
            "Alien" -> ALIEN
            "Human" -> HUMAN
            else    -> throw IllegalArgumentException()
        }
    }
}