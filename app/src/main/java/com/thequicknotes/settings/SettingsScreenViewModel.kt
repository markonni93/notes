package com.thequicknotes.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thequicknotes.data.repositories.pref.NotesDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(private val notesDataStoreRepository: NotesDataStoreRepository) : ViewModel() {

  val darkModeEnabled = notesDataStoreRepository.getIsDarkModeEnabled()

  fun setDarkModeEnabled(isDarkModeEnabled: Boolean) = viewModelScope.launch {
    notesDataStoreRepository.setIsDarkModeEnabled(isDarkModeEnabled)
  }
}