package com.example.notesdata.repositories.notes

import androidx.paging.PagingData
import com.example.notesdata.entities.NoteEntity
import com.example.notesdata.uimodel.NoteUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

  suspend fun insert(note: NoteEntity)

  fun getNotesPaginated(coroutineScope: CoroutineScope): Flow<PagingData<NoteUiModel>>
}