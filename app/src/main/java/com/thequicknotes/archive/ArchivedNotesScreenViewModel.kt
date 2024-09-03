package com.thequicknotes.archive

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArchivedNotesScreenViewModel @Inject constructor() : ViewModel() {

  init {
    Timber.d("MARKO ArchivedNotesScreenViewModel initialized")
  }

}