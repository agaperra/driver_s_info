package ru.agaperra.driversinfo.ui.navigation

sealed class Screen(val route: String) {
    object NumberScreen : Screen(NUMBER_SCREEN)
    object VRCScreen : Screen(VRC_SCREEN)
    object DLScreen : Screen(DL_SCREEN)
    object InfoScreen : Screen(INFO_SCREEN)
}

const val NUMBER_SCREEN = "number"
const val VRC_SCREEN = "vrc"
const val DL_SCREEN = "dl"
const val INFO_SCREEN = "info"
