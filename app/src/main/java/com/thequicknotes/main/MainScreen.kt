package com.thequicknotes.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.thequicknotes.R
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.navigation.CREATE_NOTE_NAVIGATION_ROUTE
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(navController: NavHostController, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedVisibilityScope) {

  val viewModel: MainViewModel = hiltViewModel<MainViewModel>()

  val items = viewModel.items.collectAsLazyPagingItems()

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberStandardBottomSheetState(
      initialValue = SheetValue.Hidden, skipHiddenState = false
    )
  )

  BaseBottomSheetScaffold(
    modifier = Modifier.Companion
      .fillMaxSize(),
    scaffoldState = bottomSheetScaffoldState,
    content = {
      Scaffold(floatingActionButton = {

        with(sharedTransitionScope) {
          FloatingActionButton(
            modifier = Modifier.sharedElement(sharedTransitionScope.rememberSharedContentState(key = "fab_button"), animatedContentScope),
            onClick = {
              navController.navigate(CREATE_NOTE_NAVIGATION_ROUTE)
            }) {
            Icon(painter = painterResource(id = R.drawable.create_note_icon), contentDescription = "Create icon", tint = Color.Unspecified)
          }
        }
      }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
        HomeScreen(
          Modifier
            .padding(paddingValues),
          items,
          showBottomSheet = {

          })
      })
    })
}