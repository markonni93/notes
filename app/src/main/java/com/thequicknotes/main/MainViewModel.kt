package com.thequicknotes.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.repositories.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  private val noteIds = mutableSetOf<Int>()

  private val defaultQuery = MutableStateFlow("")

  // TODO Check if coming back to the screen causes this to refresh

  val items = repository.getNotesPaginated().cachedIn(viewModelScope).combine(defaultQuery) { pagingData, query ->
    pagingData.filter { it.description.contains(query, ignoreCase = true) || it.title.contains(query, ignoreCase = true) }
  }

  fun searchNotes(query: String) {
    defaultQuery.value = query
  }

  fun insertNote(title: String?, text: String?, color: Color) = viewModelScope.launch {
    if (title?.isNotEmpty() == true && text?.isNotEmpty() == true) {
      repository.insert(
        NoteEntity(
          title = title,
          text = text,
          color = color,
          createdAt = Clock.System.now().toString(),
          updatedAt = Clock.System.now().toString(),
          isArchived = false
        )
      )
    }
  }

  fun deleteNote(id: Int) = viewModelScope.launch {
    repository.deleteNote(id)
  }

  fun archiveNote(id: Int) = viewModelScope.launch {
    repository.archiveNote(id)
  }

  fun deleteNotes() = viewModelScope.launch {
    repository.deleteNotes(noteIds.toList())
  }

  fun archiveNotes() = viewModelScope.launch {
    repository.archiveNotes(noteIds.toList())
  }

  fun addSelectedNotesId(id: Int) {
    noteIds.add(id)
  }
}