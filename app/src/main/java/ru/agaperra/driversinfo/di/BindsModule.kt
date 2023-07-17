package ru.agaperra.driversinfo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.agaperra.driversinfo.data.repository.CertificateRepositoryImpl
import ru.agaperra.driversinfo.data.repository.DriversInfoRepositoryImpl
import ru.agaperra.driversinfo.data.repository.LicenseRepositoryImpl
import ru.agaperra.driversinfo.data.repository.SharedPreferencesRepositoryImpl
import ru.agaperra.driversinfo.data.repository.VehicleNumberRepositoryImpl
import ru.agaperra.driversinfo.domain.repository.CertificateRepository
import ru.agaperra.driversinfo.domain.repository.DriversInfoRepository
import ru.agaperra.driversinfo.domain.repository.LicenseRepository
import ru.agaperra.driversinfo.domain.repository.SharedPreferencesRepository
import ru.agaperra.driversinfo.domain.repository.VehicleNumberRepository

@Module
@InstallIn(ViewModelComponent::class)
interface BindsModule {
    @Binds
    fun bindSPrefsRepository(repositoryImpl: SharedPreferencesRepositoryImpl): SharedPreferencesRepository

    @Binds
    fun bindVehicleRepository(vehicleRepositoryImpl: VehicleNumberRepositoryImpl): VehicleNumberRepository

    @Binds
    fun bindCertificateRepository(certificateRepositoryImpl: CertificateRepositoryImpl): CertificateRepository

    @Binds
    fun bindLicenseRepository(LicenseRepositoryImpl: LicenseRepositoryImpl): LicenseRepository

    @Binds
    fun bindDriversInfoRepository(driversInfoRepositoryImpl: DriversInfoRepositoryImpl): DriversInfoRepository
}