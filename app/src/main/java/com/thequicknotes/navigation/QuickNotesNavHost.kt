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
import com.thequicknotes.archive.ArchivedNotesScreen
import com.thequicknotes.bin.DeletedNotesScreen
import com.thequicknotes.createnote.CreateScreen
import com.thequicknotes.main.MainScreen
import com.thequicknotes.notedetails.NoteDetailsScreen
import com.thequicknotes.settings.SettingsScreen
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.ARCHIVE
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.BIN
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem.SETTINGS

data class DataForAnimation @OptIn(ExperimentalSharedTransitionApi::class) constructor(
  val transitionLayout: SharedTransitionScope, val animatedContentScope: AnimatedContentScope
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
          val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

          val title = savedStateHandle?.getLiveData<String?>(NOTE_TITLE_ARG_KEY, initialValue = null)
          val note = savedStateHandle?.getLiveData<String?>(NOTE_ARG_KEY, initialValue = null)
          val color = savedStateHandle?.getLiveData<String?>(NOTE_COLOR_ARG_KEY, initialValue = null)
          savedStateHandle?.remove<String>(NOTE_TITLE_ARG_KEY)
          savedStateHandle?.remove<String>(NOTE_ARG_KEY)
          savedStateHandle?.remove<String>(NOTE_COLOR_ARG_KEY)

          MainScreen(sharedContentStateKey = CONTENT_KEY_STATE_FAB, onNoteClicked = { id ->
            navController.navigate("$NOTE_DETAILS_NAVIGATION_ROUTE/$id")
          }, onCreateNoteClicked = {
            navController.navigate(CREATE_NOTE_NAVIGATION_ROUTE)
          }, onDrawerItemClicked = { drawerItem ->
            when (drawerItem) {
              BIN -> navController.navigate(DELETED_NOTES_ROUTE)
              ARCHIVE -> navController.navigate(ARCHIVED_NOTES_ROUTE)
              SETTINGS -> navController.navigate(NOTE_SETTINGS_ROUTE)
              else -> {

              }
            }
          },
            title = title,
            note = note,
            color = color
          )
        }
      }
      composable(CREATE_NOTE_NAVIGATION_ROUTE) {
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          CreateScreen(sharedContentStateKey = CONTENT_KEY_STATE_FAB, onBackPressed = { title, note, color ->
            navController.previousBackStackEntry?.savedStateHandle?.set(
              NOTE_TITLE_ARG_KEY, title
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
              NOTE_ARG_KEY, note
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
              NOTE_COLOR_ARG_KEY, color
            )
            navController.popBackStack()
          })
        }
      }
      composable(
        route = "$NOTE_DETAILS_NAVIGATION_ROUTE/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })
      ) {
        val id = it.arguments?.getInt("id")!!
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          NoteDetailsScreen(
            navController = navController, id = id
          )
        }
      }
      composable(NOTE_SETTINGS_ROUTE) {
        SettingsScreen()
      }

      composable(DELETED_NOTES_ROUTE) {
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          DeletedNotesScreen(onBackClicked = {
            navController.popBackStack()
          })
        }
      }

      composable(ARCHIVED_NOTES_ROUTE) {
        ArchivedNotesScreen()
      }
    }
  }
}

private const val MAIN_NAVIGATION_ROUTE = "main_screen_route"
private const val CREATE_NOTE_NAVIGATION_ROUTE = "create_note_route"
private const val NOTE_DETAILS_NAVIGATION_ROUTE = "note_details_route"
private const val DELETED_NOTES_ROUTE = "deleted_notes_route"
private const val ARCHIVED_NOTES_ROUTE = "archived_notes_route"
private const val NOTE_SETTINGS_ROUTE = "note_settings_route"
private const val CONTENT_KEY_STATE_FAB = "fab"
private const val NOTE_TITLE_ARG_KEY = "note_title_arg_key"
private const val NOTE_ARG_KEY = "note_arg_key"
private const val NOTE_COLOR_ARG_KEY = "note_color_key"