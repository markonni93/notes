package com.thequicknotes.main

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.thequicknotes.R
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.home.empty.EmptyHomeScreen
import com.thequicknotes.navigation.CREATE_NOTE_NAVIGATION_ROUTE
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
  navController: NavHostController, sharedContentStateKey: String
) {
  val animationData = LocalSharedTransitionLayoutData.current

  val viewModel: MainViewModel = hiltViewModel<MainViewModel>()

  val items = viewModel.items.collectAsLazyPagingItems()

  val isScreenEmpty by remember {
    derivedStateOf { items.itemCount == 0 }
  }

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberStandardBottomSheetState(
      initialValue = SheetValue.Hidden, skipHiddenState = false
    )
  )

  LaunchedEffect(navController.saveState() != null) {
    val title = navController.currentBackStackEntry?.savedStateHandle?.get<String>("title")
    val note = navController.currentBackStackEntry?.savedStateHandle?.get<String>("note")
    val colorString = navController.currentBackStackEntry?.savedStateHandle?.get<String>("color")
    val uLongColor = colorString?.toULong()
    val color = uLongColor?.let {
      Color(it)
    }
    viewModel.insertNote(title, note, color ?: Color.White)
    navController.saveState()?.clear()
  }

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, content = {
    Scaffold(floatingActionButton = {
      with(animationData.animatedContentScope) {
        with(animationData.transitionLayout) {
          FloatingActionButton(modifier = Modifier
            .sharedBounds(
              animationData.transitionLayout.rememberSharedContentState(key = sharedContentStateKey), animationData.animatedContentScope
            )
            .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
            .animateEnterExit(
              enter = fadeIn() + slideInVertically { it },
              exit = fadeOut() + slideOutVertically { it }
            ), onClick = {
            navController.navigate(CREATE_NOTE_NAVIGATION_ROUTE)
          }) {
            Icon(painter = painterResource(id = R.drawable.create_note_icon), contentDescription = "Create icon", tint = Color.Unspecified)
          }
        }
      }
    }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
      if (isScreenEmpty) {
        EmptyHomeScreen(modifier = Modifier.padding(paddingValues))
      } else {
        HomeScreen(Modifier.padding(paddingValues), items, showBottomSheet = {

        }, searchNotes = { query ->
          viewModel.searchNotes(query)
        }, navController
        )
      }
    })
  })
}