package com.thequicknotes.data.repositories.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.thequicknotes.data.repositories.pref.NotesDataStoreValues.DARK_MODE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesDataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : NotesDataStoreRepository {

  override suspend fun setIsDarkModeEnabled(isEnabled: Boolean) {
    dataStore.edit { settings ->
      settings[DARK_MODE_KEY] = isEnabled
    }
  }

  override fun getIsDarkModeEnabled(): Flow<Boolean> = dataStore.data.map { preferences ->
    preferences[DARK_MODE_KEY] ?: false
  }
}