package com.example.notesdata.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notesdata.entities.NoteEntity

@Dao
interface NoteDao {
  @Query("select * from note_entity")
  fun getAllNotes(): PagingSource<Int, NoteEntity>

  @Insert
  suspend fun insertAll(vararg notes: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}