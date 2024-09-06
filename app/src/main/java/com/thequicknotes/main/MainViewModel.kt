package com.thequicknotes.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.repositories.notes.NotesRepository
import com.thequicknotes.data.uimodel.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

@HiltViewModel
class
MainViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  private val noteIds = mutableSetOf<Int>()

  private val _deletingNotesState = MutableLiveData<Boolean?>()
  val deletingNotesState: LiveData<Boolean?> get() = _deletingNotesState

  private val _note = MutableLiveData<Result<NoteUiModel>>()
  val note: LiveData<Result<NoteUiModel>> get() = _note

  fun insertNote(title: String?, text: String?, color: String?) = viewModelScope.launch {
    if (title?.isNotEmpty() == true && text?.isNotEmpty() == true) {
      val uLongColor = color?.toULong()
      val noteColor = uLongColor?.let {
        Color(it)
      } ?: Color.White
      repository.insert(
        NoteEntity(
          title = title,
          text = text,
          color = noteColor,
          createdAt = Clock.System.now().toString(),
          updatedAt = Clock.System.now().toString(),
          isArchived = false,
          isDeleted = false
        )
      )
    }
  }

  fun getNote() = viewModelScope.launch {
//    try {
//      val id = noteIds.firstOrNull()
//      id?.let {
//        _note.value = repository.getNote(it)
//      }
//    } catch (e: Exception) {
//
//    }
  }

  fun deleteNote(id: Int) = viewModelScope.launch {
    //repository.deleteNote(id)
  }

  fun archiveNote() = viewModelScope.launch {
    repository.archiveNote(noteIds.first())
  }

  fun moveNotesToBin() = viewModelScope.launch {
    val result = repository.moveNotesToBin(ids = noteIds.toList())
    _deletingNotesState.value = result.isSuccess()
  }

  fun restoreNotesFromBin() = viewModelScope.launch {
    val result = repository.restoreNotesFromBin(ids = noteIds.toList())
    _deletingNotesState.value = result.isSuccess()
  }

  fun archiveNotes() = viewModelScope.launch {
    repository.archiveNotes(noteIds.toList())
  }

  fun addSelectedNotesId(id: Int) {
    noteIds.add(id)
  }

  fun clearSnackbarStatus() {
    _deletingNotesState.value = null
  }
}