package com.thequicknotes.notedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thequicknotes.data.general.Result
import com.thequicknotes.data.repositories.notes.NotesRepository
import com.thequicknotes.data.uimodel.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  private val _note = MutableLiveData<Result<NoteUiModel>>()
  val note: LiveData<Result<NoteUiModel>> get() = _note

  fun getNote(id: Int) = viewModelScope.launch {
    _note.value = repository.getNote(id)
  }
}