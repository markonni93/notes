package com.example.notes.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entities.NoteEntity
import com.example.data.repositories.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  fun insertNote(title: String?, text: String?, color: Color) = viewModelScope.launch {
    if (title?.isNotEmpty() == true && text?.isNotEmpty() == true) {
      repository.insert(NoteEntity(title = title, text = text, color = color, createdAt = "", updatedAt = ""))
    }
  }
}