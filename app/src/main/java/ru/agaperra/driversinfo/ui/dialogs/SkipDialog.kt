package ru.agaperra.driversinfo.ui.dialogs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import ru.agaperra.driversinfo.ui.theme.Coral
import ru.agaperra.driversinfo.ui.theme.RawOchre

@Composable
fun SkipDialog(
    show: Boolean,
    confirmTitle: Int,
    dismissTitle: Int,
    title: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false),
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm)
                {
                    Text(
                        fontSize = 18.sp,
                        text = stringResource(confirmTitle),
                        color = Color.Black
                    )
                }
            },
            dismissButton = {
                    TextButton(onClick = onDismiss)
                    {
                        Text(
                            fontSize = 18.sp,
                            text = stringResource(dismissTitle),
                            color = Color.Black,
                        )
                    }
            },
            title = {
                Text(
                    fontSize = 18.sp,
                    text = stringResource(title),
                    color = Color.Black
                )
            },
            containerColor = if (isSystemInDarkTheme()) RawOchre else Coral
        )
    }
}