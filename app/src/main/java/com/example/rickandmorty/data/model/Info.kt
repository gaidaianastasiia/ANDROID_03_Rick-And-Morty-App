package com.example.rickandmorty.data.model

data class Info(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any? = null
)
