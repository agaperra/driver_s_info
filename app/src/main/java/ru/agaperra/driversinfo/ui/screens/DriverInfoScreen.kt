package ru.agaperra.driversinfo.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.ui.components.InfoContent
import ru.agaperra.driversinfo.ui.dialogs.ApplicationDialog
import ru.agaperra.driversinfo.ui.navigation.Screen
import ru.agaperra.driversinfo.ui.theme.Coral
import ru.agaperra.driversinfo.ui.theme.Milk
import ru.agaperra.driversinfo.ui.theme.PurpleDark
import ru.agaperra.driversinfo.ui.theme.RawOchre

@Composable
fun InfoScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val showUpdateDialogState: Boolean by mainViewModel.showUpdateDialog.collectAsState()

    ApplicationDialog(
        show = showUpdateDialogState,
        confirmTitle = R.string.ask_update_short,
        dismissTitle = R.string.cancel,
        title = R.string.update,
        onDismiss = mainViewModel::onUpdateDialogDismiss
    ) {
        mainViewModel.deleteDriversInfo()
        mainViewModel.onUpdateDialogConfirm()
        navController.popBackStack()
        navController.navigate(Screen.VehicleScreen.route) {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }
    val isDark = if (isSystemInDarkTheme()) Milk else PurpleDark
    val notIsDark = if (isSystemInDarkTheme()) PurpleDark else Milk
    val driversInfo = mainViewModel.getDriversInfo()


    Box() {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = if (isSystemInDarkTheme()) Coral else RawOchre
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                InfoContent(driversInfo.vehicleNumber, R.string.car_number)
                InfoContent(driversInfo.certificateNumber, R.string.vrc_number)
                InfoContent(driversInfo.licenseNumber, R.string.dl_number)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            onClick = {
                                mainViewModel.onUpdateSkipDialogClicked()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = isDark),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.ask_update),
                                color = notIsDark,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}