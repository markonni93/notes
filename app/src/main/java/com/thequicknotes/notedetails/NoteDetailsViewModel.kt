package com.thequicknotes.notedetails

import androidx.lifecycle.ViewModel
import com.thequicknotes.data.repositories.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  
}