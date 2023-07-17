package ru.agaperra.driversinfo.ui.navigation

sealed class Screen(val route: String) {
    object VehicleScreen : Screen(VEHICLE_SCREEN)
    object CertificateScreen : Screen(CERTIFICATE_SCREEN)
    object LicenseScreen : Screen(LICENSE_SCREEN)
    object InfoScreen : Screen(INFO_SCREEN)
}

const val VEHICLE_SCREEN = "VEHICLE_SCREEN"
const val CERTIFICATE_SCREEN = "CERTIFICATE_SCREEN"
const val LICENSE_SCREEN = "LICENSE_SCREEN"
const val INFO_SCREEN = "info"
