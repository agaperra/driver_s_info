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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.ui.dialogs.SkipDialog
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

    val showDialogState: Boolean by mainViewModel.showDialog.collectAsState()

    SkipDialog(
        show = showDialogState,
        confirmTitle = R.string.ask_update_short,
        dismissTitle = R.string.cancel,
        title = R.string.update,
        onDismiss = mainViewModel::onDialogDismiss
    ) {
        mainViewModel.saveAll()
        mainViewModel.onDialogConfirm()
        navController.popBackStack()
        navController.navigate(Screen.NumberScreen.route) {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }
    val isDark = if (isSystemInDarkTheme()) Milk else PurpleDark
    val notIsDark = if (isSystemInDarkTheme()) PurpleDark else Milk

    Box() {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = if (isSystemInDarkTheme()) Coral else RawOchre
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.car_number),
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (mainViewModel.getAll()[0].isNullOrEmpty()) stringResource(R.string.no_data) else mainViewModel.getAll()[0]!!,
                            color = PurpleDark,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.vrc_number),
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (mainViewModel.getAll()[1].isNullOrEmpty()) stringResource(R.string.no_data) else mainViewModel.getAll()[1]!!,
                            color = PurpleDark,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.dl_number),
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (mainViewModel.getAll()[2].isNullOrEmpty()) stringResource(R.string.no_data) else mainViewModel.getAll()[2]!!,
                            color = PurpleDark,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
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
                                mainViewModel.onOpenDialogClicked()
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