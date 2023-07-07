package ru.agaperra.driversinfo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.MainViewModel.Companion.DL
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.data.dataOptions.DataOptions
import ru.agaperra.driversinfo.ui.components.BaseContent
import ru.agaperra.driversinfo.ui.components.ScreenData
import ru.agaperra.driversinfo.ui.navigation.Screen

@Composable
fun DLScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val isShow = remember { mutableStateOf(false) }
    val string = remember { mutableStateOf("") }

    BaseContent(
        navController,
        mainViewModel,
        R.string.enter_dl,
        R.string.dl_number,
        nextScreen = Screen.InfoScreen.route,
        key = DL,
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
            DataOptions.patternForDL,
            DataOptions.latinLetterListForDL,
            DataOptions.cyrillicLetterListForDL,
            navController,
            Screen.InfoScreen.route,
            DL,
            R.string.error_dl
        )
    }
}