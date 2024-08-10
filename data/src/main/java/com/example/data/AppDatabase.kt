package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.NoteDao
import com.example.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao
}