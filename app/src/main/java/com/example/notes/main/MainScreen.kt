package com.example.notes.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.notes.R
import com.example.notes.main.navigation.MainBottomNavigation
import com.example.notes.main.navigation.MainNavigationScreens
import com.example.notes.main.navigation.MainScreenNavigationConfigurations
import com.example.notes.ui.base.BaseBottomSheetScaffold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

  val navController = rememberNavController()

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberStandardBottomSheetState(
      initialValue = SheetValue.Hidden,
      skipHiddenState = false
    )
  )
  val coroutineScope = rememberCoroutineScope()

  val bottomNavigationItems = listOf(
    MainNavigationScreens.HomeScreen, MainNavigationScreens.FinishedScreen, MainNavigationScreens.SearchScreen, MainNavigationScreens.SettingsScreen
  )

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, sheetContent = {
    Column(
      modifier = Modifier
        .fillMaxSize()
    ) {
      Text("Bottom shet")
      Text("Bottom shet")
      Text("Bottom shet")
      Text("Bottom shet")
      Text("Bottom shet")
      Text("Bottom shet")
    }
  }, content = {
    Scaffold(bottomBar = {
      BottomAppBar {
        MainBottomNavigation(navController, bottomNavigationItems)
      }
    }, floatingActionButton = {
      FloatingActionButton(onClick = {
        coroutineScope.launch {
          bottomSheetScaffoldState.bottomSheetState.expand()
        }
      }) {
        Icon(painter = painterResource(id = R.drawable.create_note_icon), contentDescription = "Create icon", tint = Color.Unspecified)
      }
    }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
      MainScreenNavigationConfigurations(navController, Modifier.padding(paddingValues))
    })
  })
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    MainScreen()
  }
}