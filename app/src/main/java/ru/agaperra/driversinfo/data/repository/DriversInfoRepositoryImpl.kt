package ru.agaperra.driversinfo.data.repository

import ru.agaperra.driversinfo.domain.model.DriversInfo
import ru.agaperra.driversinfo.domain.repository.CertificateRepository
import ru.agaperra.driversinfo.domain.repository.DriversInfoRepository
import ru.agaperra.driversinfo.domain.repository.LicenseRepository
import ru.agaperra.driversinfo.domain.repository.VehicleNumberRepository
import javax.inject.Inject

class DriversInfoRepositoryImpl  @Inject constructor(
    private val vehicleNumberRepository: VehicleNumberRepository,
    private val certificateRepository: CertificateRepository,
    private val licenseRepository: LicenseRepository
):DriversInfoRepository{

    override fun saveDriversInfo(driver: DriversInfo) {
        vehicleNumberRepository.saveVehicleNumber(driver.vehicleNumber)
        certificateRepository.saveCertificate(driver.certificateNumber)
        licenseRepository.saveLicenseNumber(driver.licenseNumber)
    }

    override fun getDriversInfo(): DriversInfo =
        DriversInfo(
            vehicleNumberRepository.getVehicleNumber(),
            certificateRepository.getCertificate(),
            licenseRepository.getLicenseNumber()
        )

    override fun deleteDriversInfo() {
        vehicleNumberRepository.deleteVehicleNumber()
        certificateRepository.deleteCertificate()
        licenseRepository.deleteLicenseNumber()
    }
}