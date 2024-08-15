package com.thequicknotes.data.repositories.notes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.thequicknotes.data.dao.NoteDao
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.uimodel.NoteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NotesRepository {

  override suspend fun insert(note: NoteEntity) = noteDao.insertAll(note)

  override fun getNotesPaginated(): Flow<PagingData<NoteUiModel>> = Pager(
    config = PagingConfig(
      pageSize = 20,
      enablePlaceholders = false,
      initialLoadSize = 20
    ),
    pagingSourceFactory = { noteDao.getAllNotes() }
  ).flow
    .map { items ->
      items.map { entity ->
        NoteUiModel(id = entity.id!!, title = entity.title, description = entity.text, color = entity.color)
      }
    }
}