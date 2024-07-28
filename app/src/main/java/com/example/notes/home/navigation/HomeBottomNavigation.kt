package com.example.notes.home.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme

@Composable
fun HomeBottomNavigation(
  navController: NavHostController,
  bottomNavigationItems: List<HomeNavigationScreens>
) {
  var selectedItemRoute by remember { mutableStateOf(HomeNavigationScreens.HomeScreen.route) }

  NavigationBar {

    bottomNavigationItems.forEach { item ->
      NavigationBarItem(selected = item.route == selectedItemRoute, onClick = {
        selectedItemRoute = item.route
      }, icon = {
        Icon(
          painter = painterResource(id = item.icon),
          contentDescription = "Home navigation tab",
          tint = if (item.route == selectedItemRoute) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceBright
        )
      }, label = {
        Text(text = stringResource(id = item.label))
      })
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeBottomNavigation() {
  AppTheme {
    HomeBottomNavigation(
      rememberNavController(), listOf(
        HomeNavigationScreens.HomeScreen,
        HomeNavigationScreens.FinishedScreen,
        HomeNavigationScreens.CreateScreen,
        HomeNavigationScreens.SearchScreen,
        HomeNavigationScreens.SettingsScreen
      )
    )
  }
}