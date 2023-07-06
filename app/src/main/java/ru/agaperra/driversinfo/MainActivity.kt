package ru.agaperra.driversinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.agaperra.driversinfo.ui.navigation.SetupNavigation
import ru.agaperra.driversinfo.ui.theme.Coral
import ru.agaperra.driversinfo.ui.theme.DriversInfoTheme
import ru.agaperra.driversinfo.ui.theme.RawOchre

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class, ExperimentalAnimationApi::class,
    ExperimentalCoroutinesApi::class
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            ProvideWindowInsets {
                DriversInfoTheme() {
                    val mainViewModel: MainViewModel = hiltViewModel()

                    val systemUiController = rememberSystemUiController()
                    val isDark = isSystemInDarkTheme()
                    SideEffect { systemUiController.setSystemBarsColor(color = if (isDark) Coral else RawOchre) }
                    SetupNavigation(mainViewModel)
                }
            }
        }
    }
}

