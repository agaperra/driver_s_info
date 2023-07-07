package ru.agaperra.driversinfo.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel

@Composable
fun ScreenData(
    string: String,
    mainViewModel: MainViewModel,
    patternList: List<Regex>,
    latin: List<CharSequence?>,
    cyrillic: List<CharSequence>,
    navController: NavController,
    route: String,
    key: String,
    errorString: Int
){
    val context = LocalContext.current

    var count = 0
    for (i in patternList.indices) {
        if (patternList[i].matches(string)) {
            val array: Array<String> =
                string.toCharArray().map { it.toString() }.toTypedArray()
            val newList = array.map {
                if (mainViewModel.ifLetterInCyrillicOrInLatin(
                        it,
                        latin
                    )
                ) mainViewModel.changeLatinForCyrillic(
                    it, cyrillic, latin
                ) else if (mainViewModel.ifLetterInCyrillicOrInLatin(
                        it,cyrillic
                    )
                ) it else if (mainViewModel.ifLetterInNumbers(it.toIntOrNull()) != null && mainViewModel.ifLetterInNumbers(
                        it.toIntOrNull()
                    )!!
                ) it else null
            }
            if (newList.contains(null)) {
                continue
            } else {
                newList.joinToString("")
                mainViewModel.saveData(newList.joinToString(""), key)
                navController.popBackStack()
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
                break
            }
        } else {
            count++
        }
    }
    if (count == patternList.size) {
        Toast.makeText(
            context,
            context.getString(errorString),
            Toast.LENGTH_SHORT
        ).show()
    }

}