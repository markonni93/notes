package com.example.notesdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesdata.dao.NoteDao
import com.example.notesdata.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao
}