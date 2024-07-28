package com.example.notesdata

import com.example.notesdata.repositories.NotesRepository
import com.example.notesdata.repositories.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

  @Binds
  abstract fun bindNotesRepository(impl: NotesRepositoryImpl): NotesRepository
}