package com.example.notes.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesdata.repositories.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  val items = repository.getNotesPaginated(coroutineScope = viewModelScope)
}