package ru.agaperra.driversinfo.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.ui.screens.CertificateScreen
import ru.agaperra.driversinfo.ui.screens.InfoScreen
import ru.agaperra.driversinfo.ui.screens.LicenseScreen
import ru.agaperra.driversinfo.ui.screens.VehicleNumberScreen

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(mainViewModel: MainViewModel) {

    val navHostController =
        rememberAnimatedNavController()

    val start =
        if (mainViewModel.getUserEndState()) Screen.InfoScreen.route else Screen.VehicleScreen.route

    com.google.accompanist.navigation.animation.AnimatedNavHost(
        navController = navHostController,
        startDestination = start
    ) {
        composable(
            route = Screen.VehicleScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                VehicleNumberScreen(navHostController, mainViewModel)
            }
        )
        composable(
            route = Screen.CertificateScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                CertificateScreen(navHostController, mainViewModel)
            }
        )
        composable(
            route = Screen.LicenseScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                LicenseScreen(navHostController, mainViewModel)
            }
        )
        composable(
            route = Screen.InfoScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                InfoScreen(navHostController, mainViewModel)
            }
        )
    }
}