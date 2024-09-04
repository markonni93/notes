package com.thequicknotes.data.repositories.pref

import kotlinx.coroutines.flow.Flow

interface NotesDataStoreRepository {

  suspend fun setIsDarkModeEnabled(isEnabled: Boolean)

  fun getIsDarkModeEnabled(): Flow<Boolean>
}