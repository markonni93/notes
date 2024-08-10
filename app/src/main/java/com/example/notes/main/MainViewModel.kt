package com.example.notes.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesdata.entities.NoteEntity
import com.example.notesdata.repositories.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  fun insertNote(title: String?, text: String?) = viewModelScope.launch {
    if (title != null && text != null) {
      repository.insert(NoteEntity(title = title, text = text, createdAt = "", updatedAt = ""))
    }
  }
}