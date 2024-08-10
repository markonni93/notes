package com.example.notesdata.repositories.notes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.notesdata.dao.NoteDao
import com.example.notesdata.entities.NoteEntity
import com.example.notesdata.uimodel.NoteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NotesRepository {

  override suspend fun insert(note: NoteEntity) = noteDao.insertAll(note)

  override fun getNotesPaginated(): Flow<PagingData<NoteUiModel>> {
    return Pager(
      config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
      ), pagingSourceFactory = { noteDao.getAllNotes() }).flow.map { pagingData ->
      pagingData.map { entity ->
        NoteUiModel(id = entity.id!!, title = entity.title, description = entity.text)
      }
    }
  }
}