package ru.agaperra.driversinfo.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.agaperra.driversinfo.domain.repository.CertificateRepository
import ru.agaperra.driversinfo.domain.repository.VehicleNumberRepository
import ru.agaperra.driversinfo.domain.useCase.GetSharedPreferences
import javax.inject.Inject

class CertificateRepositoryImpl @Inject constructor(
    prefs: GetSharedPreferences,
    private val vehicleNumberRepository: VehicleNumberRepository
) : CertificateRepository {

    companion object {
        const val CERTIFICATE = "CERTIFICATE"
    }

    private var sPrefs: SharedPreferences = prefs.createSPrefs()

    override fun saveCertificate(certificate: String?) {
        if (certificate.isNullOrEmpty()){
            vehicleNumberRepository.deleteVehicleNumber()
        }
        sPrefs.edit() {
            putString(CERTIFICATE, certificate)
        }
    }

    override fun getCertificate(): String? = sPrefs.getString(CERTIFICATE, null)

    override fun deleteCertificate() {
        sPrefs.edit() {
            putString(CERTIFICATE, null)
        }
    }

}