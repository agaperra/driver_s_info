package ru.agaperra.driversinfo.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.MainViewModel.Companion.DL
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.data.dataOptions.DataOptions
import ru.agaperra.driversinfo.ui.navigation.Screen

@Composable
fun DLScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val context = LocalContext.current

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
        doOnSave = { string ->
            var count = 0
            for (i in DataOptions.patternForDL.indices) {
                if (DataOptions.patternForDL[i].matches(string)) {
                    val array: Array<String> =
                        string.toCharArray().map { it.toString() }.toTypedArray()
                    val newList = array.map {
                        if (DataOptions.ifLetterInLatinDL(it)) DataOptions.changeLatinForCyrillicDL(it) else if (DataOptions.ifLetterInCyrillicDL(
                                it
                            )
                        ) it else if (DataOptions.ifLetterInNumbers(it.toIntOrNull()) != null && DataOptions.ifLetterInNumbers(
                                it.toIntOrNull()
                            )!!
                        ) it else null
                    }
                    if (newList.contains(null)) {
                        continue
                    } else {
                        newList.joinToString("")
                        mainViewModel.saveData(newList.joinToString(""), DL)
                        mainViewModel.saveEnd(true)
                        navController.popBackStack()
                        navController.navigate(Screen.InfoScreen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        break
                    }
                } else {
                    count++
                }
            }
            if (count == DataOptions.patternForDL.size) {
                Toast.makeText(context, context.getString(R.string.error_dl), Toast.LENGTH_SHORT)
                    .show()
            }
        })
}