package com.example.rickandmorty.entity

import com.example.rickandmorty.R
import com.google.gson.annotations.SerializedName

enum class Gender(val stringRes: Int) {
    @SerializedName("Female")
    FEMALE(R.string.character_female_gender),
    @SerializedName("Male")
    MALE(R.string.character_male_gender),
    @SerializedName("unknown")
    UNKNOWN(R.string.character_unknown_gender);

    companion object {
        public fun fromValue(value: String): Gender = when (value) {
            "Female" -> FEMALE
            "Male" -> MALE
            "unknown" -> UNKNOWN
            else -> throw IllegalArgumentException()
        }
    }
}