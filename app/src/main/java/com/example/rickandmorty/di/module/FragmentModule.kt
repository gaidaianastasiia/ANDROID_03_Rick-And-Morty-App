package com.example.rickandmorty.di.module

import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.presentation.fragment.characters.CharactersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeCharactersListFragment(): CharactersListFragment
}