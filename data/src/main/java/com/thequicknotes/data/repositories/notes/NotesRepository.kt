package com.thequicknotes.data.repositories.notes

import androidx.paging.PagingData
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.general.Result
import com.thequicknotes.data.uimodel.NoteUiModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

  suspend fun insert(note: NoteEntity)

  suspend fun deleteNote(id: Int)

  suspend fun deleteNotes(ids: List<Int>)

  suspend fun archiveNotes(ids: List<Int>)

  suspend fun archiveNote(id: Int)

  suspend fun getNote(id: Int): Result<NoteUiModel>

  fun getNotesPaginated(): Flow<PagingData<NoteUiModel>>
}