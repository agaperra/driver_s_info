package ru.agaperra.driversinfo.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.MainViewModel.Companion.NUMBER
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.changeLatinForCyrillic
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.ifLetterInCyrillic
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.ifLetterInLatin
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.ifLetterInNumbers
import ru.agaperra.driversinfo.data.dataOptions.DataOptions.patternForAutoNumber
import ru.agaperra.driversinfo.ui.navigation.Screen

@Composable
fun NumberScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current

    BaseContent(
        navController,
        mainViewModel,
        R.string.enter_number,
        R.string.car_number,
        nextScreen = Screen.VRCScreen.route,
        key = NUMBER,
        doOnSkip = {
            mainViewModel.onOpenDialogClicked()
        },
        doOnSave = { string ->
            var count = 0
            for (i in patternForAutoNumber.indices) {
                if (patternForAutoNumber[i].matches(string)) {
                    val array: Array<String> =
                        string.toCharArray().map { it.toString() }.toTypedArray()
                    val newList = array.map {
                        if (ifLetterInLatin(it)) changeLatinForCyrillic(it) else if (ifLetterInCyrillic(
                                it
                            )
                        ) it else if (ifLetterInNumbers(it.toIntOrNull()) != null && ifLetterInNumbers(
                                it.toIntOrNull()
                            )!!
                        ) it else null
                    }
                    if (newList.contains(null)) {
                        continue
                    } else {
                        newList.joinToString("")
                        mainViewModel.saveData(newList.joinToString(""), NUMBER)
                        navController.popBackStack()
                        navController.navigate(Screen.VRCScreen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        break
                    }
                } else {
                    count++
                }
            }
            if (count == patternForAutoNumber.size) {
                Toast.makeText(
                    context,
                    context.getString(R.string.error_number),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
}
