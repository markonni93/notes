package com.thequicknotes.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thequicknotes.create.CreateScreen
import com.thequicknotes.main.MainScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun QuickNotesNavHost() {
  SharedTransitionLayout {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MAIN_NAVIGATION_ROUTE) {
      composable(MAIN_NAVIGATION_ROUTE) {
        MainScreen(
          navController,
          sharedTransitionScope = this@SharedTransitionLayout,
          animatedContentScope = this@composable,
          sharedContentStateKey = CONTENT_KEY_STATE_FAB
        )
      }
      composable(CREATE_NOTE_NAVIGATION_ROUTE) {
        CreateScreen(
          navController,
          sharedTransitionScope = this@SharedTransitionLayout,
          animatedContentScope = this@composable,
          sharedContentStateKey = CONTENT_KEY_STATE_FAB,
          onBackButtonClick = { _, _, _ -> })
      }
    }
  }
}

const val MAIN_NAVIGATION_ROUTE = "main_screen_route"
const val CREATE_NOTE_NAVIGATION_ROUTE = "create_note_route"
private const val CONTENT_KEY_STATE_FAB = "fab"