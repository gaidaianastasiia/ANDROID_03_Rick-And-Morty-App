package com.example.rickandmorty.data.model

enum class Gender(val value: String) {
    Female("Female"),
    Male("Male"),
    Unknown("unknown");

    companion object {
        public fun fromValue(value: String): Gender = when (value) {
            "Female" -> Female
            "Male" -> Male
            "unknown" -> Unknown
            else -> throw IllegalArgumentException()
        }
    }
}