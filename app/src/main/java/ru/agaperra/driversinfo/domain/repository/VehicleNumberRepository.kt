package ru.agaperra.driversinfo.domain.repository

interface VehicleNumberRepository {
    fun saveVehicleNumber(number: String?)
    fun getVehicleNumber(): String?
    fun deleteVehicleNumber()
}