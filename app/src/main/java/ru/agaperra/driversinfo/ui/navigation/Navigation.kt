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
import ru.agaperra.driversinfo.ui.screens.DLScreen
import ru.agaperra.driversinfo.ui.screens.InfoScreen
import ru.agaperra.driversinfo.ui.screens.NumberScreen
import ru.agaperra.driversinfo.ui.screens.VRCScreen

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(mainViewModel: MainViewModel) {

    val navHostController =
        rememberAnimatedNavController()

    val start =
        if (mainViewModel.getEnd()) Screen.InfoScreen.route else Screen.NumberScreen.route

    com.google.accompanist.navigation.animation.AnimatedNavHost(
        navController = navHostController,
        startDestination = start
    ) {
        composable(
            route = Screen.NumberScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                NumberScreen(navHostController, mainViewModel)
            }
        )
        composable(
            route = Screen.VRCScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                VRCScreen(navHostController, mainViewModel)
            }
        )
        composable(
            route = Screen.DLScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            content = {
                DLScreen(navHostController, mainViewModel)
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