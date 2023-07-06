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
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.agaperra.driversinfo.MainViewModel
import ru.agaperra.driversinfo.MainViewModel.Companion.DL
import ru.agaperra.driversinfo.R
import ru.agaperra.driversinfo.ui.dialogs.SkipDialog
import ru.agaperra.driversinfo.ui.theme.Coral
import ru.agaperra.driversinfo.ui.theme.Milk
import ru.agaperra.driversinfo.ui.theme.PurpleDark
import ru.agaperra.driversinfo.ui.theme.RawOchre

@Composable
fun BaseContent(
    navController: NavController,
    mainViewModel: MainViewModel,
    stringTitle: Int,
    stringPlaceholder: Int,
    nextScreen: String,
    key: String,
    doOnSkip: () -> Unit,
    doOnSave: (String) -> Unit,
) {
    val text = remember {
        mutableStateOf("")
    }
    val showDialogState: Boolean by mainViewModel.showDialog.collectAsState()

    SkipDialog(
        show = showDialogState,
        confirmTitle = R.string.skip,
        dismissTitle = R.string.cancel,
        title = R.string.sure,
        onDismiss = mainViewModel::onDialogDismiss
    ) {
        mainViewModel.onDialogConfirm()
        mainViewModel.saveData(null, key)
        if (key == DL)  mainViewModel.saveEnd(true)
        navController.popBackStack()
        navController.navigate(nextScreen) {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

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
                    Text(
                        text = stringResource(stringTitle),
                        color = Color.White,
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center
                    )
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
                        TextField(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            value = text.value,
                            onValueChange = {
                                text.value = it
                            },
                            label = { Text(stringResource(stringPlaceholder)) }
                        )
                    }
                }

                val isDark = if (isSystemInDarkTheme()) Milk else PurpleDark
                val notIsDark = if (isSystemInDarkTheme()) PurpleDark else Milk
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
                                doOnSkip()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = isDark),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.skip),
                                color = notIsDark,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                if (text.value.replace(" ", "").isNotEmpty()) {
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

                                    doOnSave(text.value.replace(" ", "").uppercase())
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = isDark),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.save),
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
}