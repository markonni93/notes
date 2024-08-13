package com.thequicknotes.archive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ArchiveScreen(modifier: Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    EmptyArchiveScreen(modifier = Modifier.align(Alignment.TopCenter))
  }
}