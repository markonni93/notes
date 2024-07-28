package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.AppTheme
import com.example.notes.home.HomeScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        HomeScreen(
          onHomeClick = {},
          onFinishedClick = {},
          onCreateClick = {},
          onSearchClick = {},
          onSettingsClick = {}
        )
      }
    }
  }
}