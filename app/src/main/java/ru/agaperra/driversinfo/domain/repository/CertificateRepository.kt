package ru.agaperra.driversinfo.domain.repository

interface CertificateRepository {
    fun saveCertificate(certificate: String?)
    fun getCertificate(): String?
    fun deleteCertificate()
}