package ru.agaperra.driversinfo.domain.model

import androidx.annotation.Keep

@Keep
data class DriversInfo(
    var vehicleNumber: String?,
    var certificateNumber: String?,
    var licenseNumber: String?
)
