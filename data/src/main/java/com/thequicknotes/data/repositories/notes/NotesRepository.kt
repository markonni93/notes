package com.thequicknotes.data.repositories.notes

import androidx.paging.PagingData
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.uimodel.NoteUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

  suspend fun insert(note: NoteEntity)

  suspend fun deleteNote(id: Int)

  suspend fun archiveNote(id: Int)

  fun getNotesPaginated(): Flow<PagingData<NoteUiModel>>
}