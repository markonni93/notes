package com.example.notes.main

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.create.CreateScreen
import com.example.notes.R
import com.example.notes.main.navigation.MainBottomNavigation
import com.example.notes.main.navigation.MainNavigationScreens
import com.example.notes.main.navigation.MainScreenNavigationConfigurations
import com.example.uicomponents.scaffold.BaseBottomSheetScaffold

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen() {

  val viewModel: MainViewModel = viewModel()
  val navController = rememberNavController()

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberStandardBottomSheetState(
      initialValue = SheetValue.Hidden, skipHiddenState = false
    )
  )

  var showCreateScreen by remember { mutableStateOf(false) }

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, content = {
    SharedTransitionLayout {
      AnimatedContent(targetState = showCreateScreen, label = "basic transition") { targetState ->
        if (!targetState) {
          MainContent(
            animatedVisibilityScope = this@AnimatedContent,
            sharedTransitionScope = this@SharedTransitionLayout,
            navController = navController,
            onCreateClicked = { showCreateScreen = true })
        } else {
          CreateScreen(
            animatedVisibilityScope = this@AnimatedContent,
            sharedTransitionScope = this@SharedTransitionLayout,
            onBackButtonClick = { title, text ->
              viewModel.insertNote(title, text)
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
  onCreateClicked: () -> Unit
) {
  val bottomNavigationItems = listOf(
    MainNavigationScreens.HomeScreen, MainNavigationScreens.FinishedScreen, MainNavigationScreens.SearchScreen, MainNavigationScreens.SettingsScreen
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
    MainScreenNavigationConfigurations(navController, Modifier.padding(paddingValues))
  })
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    MainScreen()
  }
}