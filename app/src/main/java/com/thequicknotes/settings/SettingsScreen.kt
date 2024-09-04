package com.thequicknotes.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thequicknotes.R
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold
import com.thequicknotes.uicomponents.topbar.DefaultTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBackClicked: () -> Unit) {


  val viewModel: SettingsScreenViewModel = hiltViewModel<SettingsScreenViewModel>()
  val darkModeEnabled = viewModel.darkModeEnabled.collectAsState(initial = false)

  BaseBottomSheetScaffold(scaffoldState = rememberBottomSheetScaffoldState(), topBar = {
    DefaultTopBar(R.string.settings_screen_title, onNavigationIconClick = onBackClicked)
  }, content = {
    Column(modifier = Modifier.fillMaxSize()) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .clickable {
            viewModel.setDarkModeEnabled(!darkModeEnabled.value)
            // darkModeEnabled = !darkModeEnabled
          },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = stringResource(id = R.string.settings_screen_dark_mode), style = MaterialTheme.typography.titleLarge)
        Switch(checked = darkModeEnabled.value, onCheckedChange = {
          viewModel.setDarkModeEnabled(it)
          //darkModeEnabled = !darkModeEnabled
        })
      }
    }
  })
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
  SettingsScreen {

  }
}