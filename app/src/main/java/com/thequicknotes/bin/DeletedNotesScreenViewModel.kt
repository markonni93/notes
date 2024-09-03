package com.thequicknotes.bin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DeletedNotesScreenViewModel @Inject constructor() : ViewModel() {

  init {
    Timber.d("MARKO DeletedScreenViewModel initialized")
  }

}