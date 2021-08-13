package com.example.rickandmorty

import com.example.rickandmorty.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Application: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<Application?> {
        return DaggerApplicationComponent.factory().create(this)
    }
}