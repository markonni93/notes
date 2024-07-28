package com.example.notesdata.repositories

import com.example.notesdata.dao.NoteDao
import com.example.notesdata.entities.NoteEntity
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NotesRepository {

  override suspend fun insert(note: NoteEntity) = noteDao.insertAll(note)
}