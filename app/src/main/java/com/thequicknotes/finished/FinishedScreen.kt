package com.thequicknotes.finished

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FinishedScreen(modifier: Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    EmptyFinishedScreen(modifier = Modifier.align(Alignment.TopCenter))
  }
}