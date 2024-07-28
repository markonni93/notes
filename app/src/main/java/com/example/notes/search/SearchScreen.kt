package com.example.notes.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(modifier: Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    Text("Search", modifier = Modifier.align(Alignment.Center))
  }
}