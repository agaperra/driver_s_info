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
    navController: NavController,
    route: String,
    errorString: Int
) {
    val context = LocalContext.current

    mainViewModel.checkString(
        navController.currentDestination?.route,
        string,
        {
            navController.popBackStack()
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
        },
        {
            Toast.makeText(
                context,
                context.getString(errorString),
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}