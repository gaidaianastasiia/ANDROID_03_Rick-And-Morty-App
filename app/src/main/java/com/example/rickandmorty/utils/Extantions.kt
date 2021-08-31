package com.example.rickandmorty.utils

import com.example.rickandmorty.data.model.CharacterData
import com.example.rickandmorty.entity.Character

fun CharacterData.toCharacters() = Character(id, image, name, status, species, gender)