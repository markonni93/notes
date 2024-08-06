package com.example.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(modifier: Modifier = Modifier, onBackButtonClick: () -> Unit) {
  Scaffold(modifier = modifier.fillMaxSize(), topBar = {
    TopAppBar(title = {}, navigationIcon = {
      IconButton(onClick = onBackButtonClick) {
        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back button", tint = Color.Unspecified)
      }
    })
  }, content = { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
    }
  })
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateScreen() {
  CreateScreen(modifier = Modifier, onBackButtonClick = {})
}