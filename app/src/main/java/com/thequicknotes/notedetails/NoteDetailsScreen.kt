package com.thequicknotes.notedetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thequicknotes.data.general.Result
import com.thequicknotes.data.model.NoteColor
import com.thequicknotes.uicomponents.topbar.DefaultTopBar

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteDetailsScreen(
  navController: NavController, id: Int, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedVisibilityScope
) {

  val viewModel: NoteDetailsViewModel = hiltViewModel<NoteDetailsViewModel>()
  viewModel.getNote(id)

  val note = viewModel.note.observeAsState()

  val error by remember {
    derivedStateOf { note.value is Result.Error }
  }

  val noteDetails by remember {
    derivedStateOf {
      (note.value as? Result.Success)?.data
    }
  }

  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(error) {
    if (error) {
      snackbarHostState.showSnackbar("Something went wrong")
    }
  }

  with(sharedTransitionScope) {
    Scaffold(modifier = Modifier
      .sharedBounds(rememberSharedContentState(key = "details_$id"), animatedVisibilityScope = animatedContentScope)
      .skipToLookaheadSize(), topBar = {
      DefaultTopBar {
        navController.popBackStack()
      }
    }, snackbarHost = {
      if (error) {
        SnackbarHost(hostState = snackbarHostState)
      }
    }) {
      Column(
        modifier = Modifier
          .padding(it)
          .background(NoteColor.BLUE.color)
          .fillMaxSize()
      ) {
        Text(text = noteDetails?.description ?: "")
      }
    }
  }
}