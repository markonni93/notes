package com.thequicknotes.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thequicknotes.data.entities.NoteEntity

@Dao
interface NoteDao {
  @Query("select * from note_entity")
  fun getAllNotes(): PagingSource<Int, NoteEntity>

  @Query("select * from note_entity limit :limit offset :offset")
  suspend fun getAllNotesWithQuery(limit: Int, offset: Int?): List<NoteEntity>

  @Insert
  suspend fun insertAll(vararg notes: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}