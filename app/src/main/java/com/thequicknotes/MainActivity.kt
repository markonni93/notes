package com.thequicknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.thequicknotes.data.repositories.pref.NotesDataStoreRepository
import com.thequicknotes.navigation.QuickNotesNavHost
import com.thequicknotes.uicomponents.theme.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var dataStoreRepo: NotesDataStoreRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val isDarkMode = dataStoreRepo.getIsDarkModeEnabled().collectAsState(initial = false)

      AppTheme(darkTheme = isDarkMode.value, dynamicColor = false) {
        QuickNotesNavHost()
      }
    }
  }
}