package ru.agaperra.driversinfo.domain.repository

interface LicenseRepository {
    fun saveLicenseNumber(license: String?)
    fun getLicenseNumber(): String?
    fun deleteLicenseNumber()
}