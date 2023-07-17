package ru.agaperra.driversinfo.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.agaperra.driversinfo.domain.repository.LicenseRepository
import ru.agaperra.driversinfo.domain.useCase.GetSharedPreferences
import javax.inject.Inject

class LicenseRepositoryImpl @Inject constructor(
    prefs: GetSharedPreferences
) : LicenseRepository {

    companion object {
        const val LICENSE = "LICENSE"
    }

    private var sPrefs: SharedPreferences = prefs.createSPrefs()

    override fun saveLicenseNumber(license: String?) {
      sPrefs.edit {
          putString(LICENSE, license)
      }
    }

    override fun getLicenseNumber(): String? = sPrefs.getString(LICENSE, null)

    override fun deleteLicenseNumber() {
        sPrefs.edit() {
            putString(LICENSE, null)
        }
    }

}