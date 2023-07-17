package ru.agaperra.driversinfo.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.agaperra.driversinfo.domain.repository.VehicleNumberRepository
import ru.agaperra.driversinfo.domain.useCase.GetSharedPreferences
import javax.inject.Inject

class VehicleNumberRepositoryImpl @Inject constructor(
    prefs: GetSharedPreferences
) : VehicleNumberRepository {

    companion object {
        const val VEHICLE = "VEHICLE"
    }

    private var sPrefs: SharedPreferences = prefs.createSPrefs()

    override fun saveVehicleNumber(number: String?) {
        sPrefs.edit() {
            putString(VEHICLE, number)
        }
    }

    override fun getVehicleNumber(): String? = sPrefs.getString(VEHICLE, null)

    override fun deleteVehicleNumber() {
        sPrefs.edit() {
            putString(VEHICLE, null)
        }
    }

}