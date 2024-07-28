package com.example.notesdata

import android.content.Context
import androidx.room.Room
import com.example.notesdata.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
    return appDatabase.noteDao()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(
      context = appContext,
      AppDatabase::class.java,
      "notes_database"
    ).build()
  }
}