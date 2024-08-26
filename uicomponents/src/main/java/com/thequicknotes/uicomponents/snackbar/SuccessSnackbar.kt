package com.thequicknotes.uicomponents.snackbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SuccessSnackbar(snackbarData: SnackbarData) {
  Snackbar(snackbarData = snackbarData, shape = RoundedCornerShape(28.dp))
}