package com.thequicknotes.notedetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.thequicknotes.data.model.NoteColor
import com.thequicknotes.uicomponents.topbar.DefaultTopBar

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteDetailsScreen(
  navController: NavController,
  id: Int,
  sharedTransitionScope: SharedTransitionScope,
  animatedContentScope: AnimatedVisibilityScope
) {
  with(sharedTransitionScope) {
    Scaffold(modifier = Modifier.sharedElement(rememberSharedContentState(key = "details_$id"), animatedVisibilityScope = animatedContentScope),
      topBar = {
        DefaultTopBar {
          navController.popBackStack()
        }
      }) {
      Column(
        modifier = Modifier
          .padding(it)
          .background(NoteColor.BLUE.color)
          .fillMaxSize()
      ) {
        Text(text = "Note details")
      }
    }
  }
}