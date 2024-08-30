package com.thequicknotes.uicomponents.snackbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorSnackbar(snackbarData: SnackbarData) {
  Snackbar(
    modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues()),
    snackbarData = snackbarData,
    shape = RoundedCornerShape(28.dp),
    containerColor = MaterialTheme.colorScheme.onErrorContainer,
    contentColor = MaterialTheme.colorScheme.error,
    actionColor = MaterialTheme.colorScheme.onError,
    actionContentColor = MaterialTheme.colorScheme.errorContainer,
  )
}