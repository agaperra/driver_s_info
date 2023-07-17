package ru.agaperra.driversinfo.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.agaperra.driversinfo.domain.repository.SharedPreferencesRepository
import ru.agaperra.driversinfo.domain.useCase.GetSharedPreferences
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    prefs: GetSharedPreferences
): SharedPreferencesRepository {

    companion object{
        const val USER_END = "USER_END"
    }

    private var sPrefs: SharedPreferences = prefs.createSPrefs()

    override fun saveUserEndState(end: Boolean) {
        sPrefs.edit() {
            putBoolean(USER_END, end)
        }
    }

    override fun getUserEndState(): Boolean =
        sPrefs.getBoolean(USER_END, false)

}