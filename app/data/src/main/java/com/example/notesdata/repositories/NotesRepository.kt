package com.example.notesdata.repositories

import com.example.notesdata.entities.NoteEntity

interface NotesRepository {

  suspend fun insert(note: NoteEntity)
}