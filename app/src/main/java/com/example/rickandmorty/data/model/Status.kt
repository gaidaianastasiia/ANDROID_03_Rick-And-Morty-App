package com.example.rickandmorty.data.model

enum class Status(val value: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("unknown");

    companion object {
        public fun fromValue(value: String): Status = when (value) {
            "Alive" -> Alive
            "Dead" -> Dead
            "unknown" -> Unknown
            else -> throw IllegalArgumentException()
        }
    }
}