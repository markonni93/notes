package com.thequicknotes.data

import com.thequicknotes.data.repositories.notes.NotesRepository
import com.thequicknotes.data.repositories.notes.NotesRepositoryImpl
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