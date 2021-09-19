package com.example.rickandmorty.di.module

import com.example.rickandmorty.di.scope.ActivityScope
import com.example.rickandmorty.presentation.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): MainActivity
}