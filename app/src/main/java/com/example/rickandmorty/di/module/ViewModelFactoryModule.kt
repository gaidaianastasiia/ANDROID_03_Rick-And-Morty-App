package com.example.rickandmorty.di.module

import com.example.rickandmorty.presentation.activity.MainViewModel
import com.example.rickandmorty.presentation.base.ViewModelAssistedFactory
import com.example.rickandmorty.presentation.fragment.characters.CharactersListViewModel
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    // >> Activities

    @Binds
    fun bindMainViewModelAssistedFactory(
        viewModelFactory: MainViewModel.Factory
    ): ViewModelAssistedFactory<MainViewModel>

    // >> Activities

    // >> Fragments

    @Binds
    fun bindCharactersListViewModelAssistedFactory(
        viewModelFactory: CharactersListViewModel.Factory
    ) : ViewModelAssistedFactory<CharactersListViewModel>

    // >> Fragments
}