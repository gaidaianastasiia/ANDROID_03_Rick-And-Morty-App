package com.example.rickandmorty.entity

import com.example.rickandmorty.R
import com.google.gson.annotations.SerializedName

enum class Status(val stringRes: Int) {
    @SerializedName("Alive")
    ALIVE(R.string.character_alive_status),
    @SerializedName("Dead")
    DEAD(R.string.character_dead_status),
    @SerializedName("unknown")
    UNKNOWN(R.string.character_unknown_status);

    companion object {
        public fun fromValue(value: String): Status = when (value) {
            "Alive" -> ALIVE
            "Dead" -> DEAD
            "unknown" -> UNKNOWN
            else -> throw IllegalArgumentException()
        }
    }
}