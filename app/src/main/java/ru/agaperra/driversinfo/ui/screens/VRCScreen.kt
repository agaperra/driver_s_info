package ru.agaperra.driversinfo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.MainViewModel.Companion.VRC
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.cyrillicLetterListForVRC
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.latinLetterListForVRC
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.patternForVRC
import ru.agaperra.driversinfo.ui.components.BaseContent
import ru.agaperra.driversinfo.ui.components.ScreenData
import ru.agaperra.driversinfo.ui.navigation.Screen

@Composable
fun VRCScreen(
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
        nextScreen = Screen.DLScreen.route,
        key = VRC,
        doOnSkip = {
            mainViewModel.onOpenDialogClicked()
        },
        doOnSave = { str ->
            string.value = str
            isShow.value = true
        })
    if (isShow.value) {

        ScreenData(
            string.value,
            mainViewModel,
            patternForVRC,
            latinLetterListForVRC,
            cyrillicLetterListForVRC,
            navController,
            Screen.DLScreen.route,
            VRC,
            R.string.error_vrc
        )
    }

}