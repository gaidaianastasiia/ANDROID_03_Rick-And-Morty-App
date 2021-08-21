package com.example.rickandmorty.di.component

import com.example.rickandmorty.Application
import com.example.rickandmorty.di.module.ApplicationModule
import com.example.rickandmorty.di.module.NetworkModule
import com.example.rickandmorty.di.module.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<Application> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<Application>
}
