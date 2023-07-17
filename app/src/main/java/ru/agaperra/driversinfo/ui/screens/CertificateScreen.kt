package ru.agaperra.driversinfo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.ui.components.BaseContent
import ru.agaperra.driversinfo.ui.components.ScreenData
import ru.agaperra.driversinfo.ui.navigation.Screen

@Composable
fun CertificateScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val isShow = remember { mutableStateOf(false) }
    val string = remember { mutableStateOf("") }

    BaseContent(
        navController,
        mainViewModel,
        R.string.enter_vrc,
        R.string.vrc_number,
        nextScreen = Screen.LicenseScreen.route,
        currentScreen = Screen.CertificateScreen.route,
        doOnSkip = {
            mainViewModel.onOpenSkipDialogClicked()
        },
        doOnSaveData = {
            string.value = it
            isShow.value = true
        })
    if (isShow.value) {
        ScreenData(
            string.value,
            mainViewModel,
            navController,
            Screen.LicenseScreen.route,
            R.string.error_vrc
        )
    }

}