package ru.agaperra.driversinfo.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.MainViewModel.Companion.VRC
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.data.dataOptions.DataOptions
import ru.agaperra.driversinfo.ui.navigation.Screen

@Composable
fun VRCScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current

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
        doOnSave = { string ->
            var count = 0
            for (i in DataOptions.patternForVRC.indices) {
                if (DataOptions.patternForVRC[i].matches(string)) {
                    val array: Array<String> =
                        string.toCharArray().map { it.toString() }.toTypedArray()
                    val newList = array.map {
                        if (DataOptions.ifLetterInLatinVRC(it))
                            DataOptions.changeLatinForCyrillicVRC(it)
                        else if (DataOptions.ifLetterInCyrillicVRC(it))
                            it
                        else if (DataOptions.ifLetterInNumbers(it.toIntOrNull()) != null &&
                            DataOptions.ifLetterInNumbers(it.toIntOrNull())!!
                        )
                            it
                        else null
                    }
                    if (newList.contains(null)) {
                        continue
                    } else {
                        newList.joinToString("")
                        mainViewModel.saveData(newList.joinToString(""), VRC)
                        navController.popBackStack()
                        navController.navigate(Screen.DLScreen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        break
                    }
                } else {
                    count++
                }
            }
            if (count == DataOptions.patternForVRC.size) {
                Toast.makeText(context, context.getString(R.string.error_vrc), Toast.LENGTH_SHORT)
                    .show()
            }
        })
}