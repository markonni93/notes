package com.thequicknotes.data.repositories.notes

import androidx.paging.PagingData
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.general.UiState
import com.thequicknotes.data.uimodel.NoteUiModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

  suspend fun insert(note: NoteEntity)

  //suspend fun deleteNote(id: Int)

  suspend fun moveNotesToBin(ids: List<Int>): UiState<Unit>

  suspend fun restoreNotesFromBin(ids: List<Int>): UiState<Unit>

  suspend fun archiveNotes(ids: List<Int>)

  suspend fun archiveNote(id: Int)

  suspend fun getNote(id: Int): UiState<NoteUiModel>

  suspend fun emptyBin()

  fun getNotesPaginated(): Flow<PagingData<NoteUiModel>>

  fun getDeletedNotesPaginated(): Flow<PagingData<NoteUiModel>>

  fun getArchivedNotesPaginated(): Flow<PagingData<NoteUiModel>>
}