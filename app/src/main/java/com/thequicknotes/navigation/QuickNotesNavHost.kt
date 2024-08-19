package com.thequicknotes.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thequicknotes.createnote.CreateScreen
import com.thequicknotes.main.MainScreen
import com.thequicknotes.notedetails.NoteDetailsScreen

data class DataForAnimation @OptIn(ExperimentalSharedTransitionApi::class) constructor(
  val transitionLayout: SharedTransitionScope,
  val animatedContentScope: AnimatedContentScope
)

val LocalSharedTransitionLayoutData = compositionLocalOf<DataForAnimation> { error("No data provided") }


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun QuickNotesNavHost() {
  SharedTransitionLayout {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MAIN_NAVIGATION_ROUTE) {
      composable(MAIN_NAVIGATION_ROUTE) { entry ->
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          MainScreen(
            navController,
            sharedContentStateKey = CONTENT_KEY_STATE_FAB,
          )
        }
      }
      composable(CREATE_NOTE_NAVIGATION_ROUTE) {
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          CreateScreen(
            navController,
            sharedContentStateKey = CONTENT_KEY_STATE_FAB
          )
        }
      }
      composable(
        route = "$NOTE_DETAILS_NAVIGATION_ROUTE/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
      ) {
        val id = it.arguments?.getInt("id")!!
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          NoteDetailsScreen(
            navController = navController,
            id = id
          )
        }
      }
    }
  }
}

const val MAIN_NAVIGATION_ROUTE = "main_screen_route"
const val CREATE_NOTE_NAVIGATION_ROUTE = "create_note_route"
const val NOTE_DETAILS_NAVIGATION_ROUTE = "note_details_route"
private const val CONTENT_KEY_STATE_FAB = "fab"