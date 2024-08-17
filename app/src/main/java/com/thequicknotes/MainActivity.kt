package com.thequicknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thequicknotes.main.MainScreen
import com.thequicknotes.navigation.QuickNotesNavHost
import com.thequicknotes.uicomponents.theme.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      AppTheme {
        QuickNotesNavHost()
        //MainScreen(navController, this@SharedTransitionLayout, this@composable)
      }
    }
  }
}