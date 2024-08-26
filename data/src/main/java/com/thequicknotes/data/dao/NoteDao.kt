package com.thequicknotes.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.thequicknotes.data.entities.NoteEntity

@Dao
interface NoteDao {
  @Query("select * from note_entity where is_archived = 0 and is_deleted = 0")
  fun getAllNotes(): PagingSource<Int, NoteEntity>

  @Query("delete from note_entity where id like :id")
  suspend fun deleteNoteById(id: Int): Int

  @Query("update note_entity set is_deleted = 1 where id in (:ids)")
  suspend fun moveNotesToBin(ids: List<Int>): Int

  @Query("update note_entity set is_deleted = 0 where id in (:ids)")
  suspend fun restoreNotesFromBin(ids: List<Int>): Int

  @Transaction
  suspend fun archiveNotes(ids: List<Int>) {
    ids.forEach { id ->
      archiveNote(id)
    }
  }

  @Query("update note_entity set is_archived = 1 where id like :id")
  suspend fun archiveNote(id: Int)

  @Query("select * from note_entity where id like :id")
  suspend fun getNoteById(id: Int): NoteEntity

  @Insert
  suspend fun insertAll(vararg notes: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}