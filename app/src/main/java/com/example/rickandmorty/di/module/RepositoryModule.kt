package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.data.repositories.RemoteCharacterRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindCharacterRepository(remoteCharacterRepository: RemoteCharacterRepository): CharacterRepository
}