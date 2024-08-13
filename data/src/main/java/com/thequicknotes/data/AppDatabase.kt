package com.thequicknotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thequicknotes.data.converters.ColorConverter
import com.thequicknotes.data.dao.NoteDao
import com.thequicknotes.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
@TypeConverters(ColorConverter::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao
}