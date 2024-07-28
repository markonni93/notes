package com.example.notes.ui.base

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheetScaffold(
  modifier: Modifier = Modifier,
  topBar: @Composable (() -> Unit)? = null,
  sheetContent: @Composable (() -> Unit)? = null,
  snackbar: @Composable (() -> Unit)? = null,
  content: @Composable (() -> Unit)? = null
) {
  BottomSheetScaffold(
    modifier = modifier,
    topBar = topBar,
    content = {
      content?.invoke()
    },
    sheetContent = {
      sheetContent?.invoke()
    },
    sheetPeekHeight = 0.dp
  )
}