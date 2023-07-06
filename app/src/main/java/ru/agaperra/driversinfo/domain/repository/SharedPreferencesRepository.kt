package ru.agaperra.driversinfo.domain.repository

interface SharedPreferencesRepository {
    fun saveData(number: String?, key: String)
    fun saveEnd(end: Boolean)
    fun getAll(): ArrayList<String?>
    fun getEnd(): Boolean
    fun saveAll()
}