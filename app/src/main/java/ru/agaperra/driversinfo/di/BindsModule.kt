package ru.agaperra.driversinfo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.agaperra.driversinfo.data.repository.SharedPreferencesRepositoryImpl
import ru.agaperra.driversinfo.domain.repository.SharedPreferencesRepository

@Module
@InstallIn(ViewModelComponent::class)
interface BindsModule {
    @Binds
    fun bindSPrefsRepository(repositoryImpl: SharedPreferencesRepositoryImpl): SharedPreferencesRepository
}