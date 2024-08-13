package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.converters.ColorConverter
import com.example.data.dao.NoteDao
import com.example.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
@TypeConverters(ColorConverter::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao
}