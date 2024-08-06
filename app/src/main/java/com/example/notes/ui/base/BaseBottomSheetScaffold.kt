package com.example.notes.ui.base

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheetScaffold(
  modifier: Modifier = Modifier,
  scaffoldState: BottomSheetScaffoldState,
  topBar: @Composable (() -> Unit)? = null,
  sheetContent: @Composable (() -> Unit)? = null,
  snackbar: @Composable (() -> Unit)? = null,
  content: @Composable (() -> Unit)? = null
) {
  BottomSheetScaffold(
    modifier = modifier,
    scaffoldState = scaffoldState,
    topBar = topBar,
    content = {
      content?.invoke()
    },
    sheetContent = {
      sheetContent?.invoke()
    },
    sheetPeekHeight = 0.dp,
    sheetContainerColor = Color.Blue,
    sheetContentColor = Color.Green,
    sheetDragHandle = null,
    sheetShape = RectangleShape

  )
}