package ru.agaperra.driversinfo.domain.repository

interface SharedPreferencesRepository {
    fun saveUserEndState(end: Boolean)
    fun getUserEndState(): Boolean
}