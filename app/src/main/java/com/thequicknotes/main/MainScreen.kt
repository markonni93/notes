package com.thequicknotes.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thequicknotes.R
import com.thequicknotes.create.CreateScreen
import com.thequicknotes.main.navigation.MainBottomNavigation
import com.thequicknotes.main.navigation.MainNavigationScreens
import com.thequicknotes.main.navigation.MainScreenBottomSheetConfiguration
import com.thequicknotes.main.navigation.MainScreenNavigationConfigurations
import com.thequicknotes.uicomponents.bottomsheets.ArchiveBottomSheet
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold
import com.thequicknotes.uicomponents.theme.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

  val viewModel: MainViewModel = viewModel()
  val navController = rememberNavController()

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberStandardBottomSheetState(
      initialValue = SheetValue.Hidden, skipHiddenState = false
    )
  )

  var bottomSheetConfig by remember {
    mutableStateOf<MainScreenBottomSheetConfiguration?>(null)
  }

  val isBottomSheetVisible by remember {
    derivedStateOf { bottomSheetScaffoldState.bottomSheetState.isVisible }
  }

  var showCreateScreen by remember { mutableStateOf(false) }
  val coroutineScope = rememberCoroutineScope()

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, sheetContent = {
    bottomSheetConfig?.let {
      when (it) {
        is MainScreenBottomSheetConfiguration.HomeScreenBottomSheetConfiguration -> {
          if (isBottomSheetVisible) {
            ArchiveBottomSheet(state = bottomSheetScaffoldState.bottomSheetState, id = it.id, onDismissRequest = {
              coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
              }
            }, onDeleteClicked = {
              coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
              }
              viewModel.deleteNote(id = it)
            }, onArchiveClicked = {
              coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
              }
              viewModel.archiveNote(id = it)
            })
          }
        }
      }
    }
  }, content = {
    SharedTransitionLayout {
      AnimatedContent(targetState = showCreateScreen, label = "basic transition") { targetState ->
        if (!targetState) {
          MainContent(
            animatedVisibilityScope = this@AnimatedContent,
            sharedTransitionScope = this@SharedTransitionLayout,
            navController = navController,
            onCreateClicked = { showCreateScreen = true },
            showBottomSheet = { data ->
              when (data) {
                is MainScreenBottomSheetConfiguration.HomeScreenBottomSheetConfiguration -> bottomSheetConfig = data
              }
              coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.show()
              }
            })
        } else {
          CreateScreen(
            animatedVisibilityScope = this@AnimatedContent,
            sharedTransitionScope = this@SharedTransitionLayout,
            onBackButtonClick = { title, text, color ->
              viewModel.insertNote(title, text, color)
              showCreateScreen = false
            }
          )
        }
      }
    }
  })
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MainContent(
  sharedTransitionScope: SharedTransitionScope,
  animatedVisibilityScope: AnimatedVisibilityScope,
  navController: NavHostController,
  onCreateClicked: () -> Unit,
  showBottomSheet: (MainScreenBottomSheetConfiguration) -> Unit
) {
  val bottomNavigationItems = listOf(
    MainNavigationScreens.HomeScreen, MainNavigationScreens.ArchiveScreen, MainNavigationScreens.SettingsScreen
  )

  Scaffold(bottomBar = {
    BottomAppBar {
      MainBottomNavigation(navController, bottomNavigationItems)
    }
  }, floatingActionButton = {
    with(sharedTransitionScope) {
      FloatingActionButton(
        modifier = Modifier.sharedElement(rememberSharedContentState(key = "floating"), animatedVisibilityScope = animatedVisibilityScope),
        onClick = onCreateClicked
      ) {
        Icon(painter = painterResource(id = R.drawable.create_note_icon), contentDescription = "Create icon", tint = Color.Unspecified)
      }
    }
  }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
    MainScreenNavigationConfigurations(navController, Modifier.padding(paddingValues), showBottomSheet = showBottomSheet)
  })
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    MainScreen()
  }
}