package com.thequicknotes.bin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.thequicknotes.data.repositories.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DeletedNotesScreenViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

  private val defaultQuery = MutableStateFlow("")

  val items = repository.getDeletedNotesPaginated().cachedIn(viewModelScope).combine(defaultQuery) { pagingData, query ->
    pagingData.filter { it.description.contains(query, ignoreCase = true) || it.title.contains(query, ignoreCase = true) }
  }

}