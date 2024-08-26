package com.thequicknotes.data.repositories.notes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.thequicknotes.data.dao.NoteDao
import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.general.Result
import com.thequicknotes.data.uimodel.NoteUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NotesRepository {

  override suspend fun insert(note: NoteEntity) = withContext(Dispatchers.IO) {
    try {
      noteDao.insertAll(note)
    } catch (e: Exception) {

    }
  }

  override suspend fun moveNotesToBin(ids: List<Int>) = withContext(Dispatchers.IO) {
    try {
      val result = noteDao.moveNotesToBin(ids)
      when {
        result == 0 ->
          Result.Error(Exception("Error deleting notes"))

        result > 0 ->
          Result.Success(Unit)

        else -> Result.Error(Exception("Error deleting notes"))
      }
    } catch (e: Exception) {
      Result.Error(e)
    }
  }

  override suspend fun restoreNotesFromBin(ids: List<Int>)= withContext(Dispatchers.IO) {
    try {
      val result = noteDao.restoreNotesFromBin(ids)
      when {
        result == 0 ->
          Result.Error(Exception("Error deleting notes"))

        result > 0 ->
          Result.Success(Unit)

        else -> Result.Error(Exception("Error deleting notes"))
      }
    } catch (e: Exception) {
      Result.Error(e)
    }
  }

  override suspend fun archiveNote(id: Int) = noteDao.archiveNote(id)

  override suspend fun archiveNotes(ids: List<Int>) = noteDao.archiveNotes(ids)

  override suspend fun getNote(id: Int) = withContext(Dispatchers.IO) {
    try {
      val noteEntity = noteDao.getNoteById(id)
      Result.Success(NoteUiModel(id = noteEntity.id!!, title = noteEntity.title, description = noteEntity.text, color = noteEntity.color))
    } catch (e: Exception) {
      Result.Error(e)
    }
  }

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