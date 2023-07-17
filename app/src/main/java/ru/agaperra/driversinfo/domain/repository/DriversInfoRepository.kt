package ru.agaperra.driversinfo.domain.repository

import ru.agaperra.driversinfo.domain.model.DriversInfo

interface DriversInfoRepository {
    fun saveDriversInfo(driver: DriversInfo)
    fun getDriversInfo(): DriversInfo
    fun deleteDriversInfo()
}