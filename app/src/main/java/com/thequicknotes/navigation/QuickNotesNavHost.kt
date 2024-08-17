package com.thequicknotes.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thequicknotes.createnote.CreateScreen
import com.thequicknotes.main.MainScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun QuickNotesNavHost() {
  SharedTransitionLayout {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MAIN_NAVIGATION_ROUTE) {
      composable(MAIN_NAVIGATION_ROUTE) { entry ->
        val title = entry.savedStateHandle.get<String>("title")
        val note = entry.savedStateHandle.get<String>("note")
        val colorString = entry.savedStateHandle.get<String>("color")
        val uLongColor = colorString?.toULong()
        val color = uLongColor?.let {
          Color(it)
        }

        MainScreen(
          navController,
          sharedTransitionScope = this@SharedTransitionLayout,
          animatedContentScope = this@composable,
          sharedContentStateKey = CONTENT_KEY_STATE_FAB,
          title,
          note,
          color ?: Color.White
        )
      }
      composable(CREATE_NOTE_NAVIGATION_ROUTE) {
        CreateScreen(
          navController,
          sharedTransitionScope = this@SharedTransitionLayout,
          animatedContentScope = this@composable,
          sharedContentStateKey = CONTENT_KEY_STATE_FAB
        )
      }
    }
  }
}

const val MAIN_NAVIGATION_ROUTE = "main_screen_route"
const val CREATE_NOTE_NAVIGATION_ROUTE = "create_note_route"
private const val CONTENT_KEY_STATE_FAB = "fab"