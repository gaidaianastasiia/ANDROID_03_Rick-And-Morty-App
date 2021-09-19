package com.example.rickandmorty.presentation.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface BaseViewModelAssistedFactory<T: ViewModel>: ViewModelAssistedFactory<T> {
    fun create(savedStateHandle: SavedStateHandle): T
}