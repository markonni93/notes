package com.example.notesdata.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notesdata.entities.NoteEntity

@Dao
interface NoteDao {
  @Query("select * from note_entity")
  suspend fun getAllNotes(): List<NoteEntity>

  @Insert
  suspend fun insertAll(vararg notes: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}