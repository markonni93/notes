package com.example.notes.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme

@Composable
fun HomeScreen(modifier: Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    EmptyHomeScreen(modifier = Modifier.align(Alignment.TopCenter))
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    HomeScreen(modifier = Modifier)
  }
}