package com.thequicknotes.data.entities

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_entity")
data class NoteEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int? = null,
  @ColumnInfo(name = "title")
  val title: String,
  @ColumnInfo(name = "text")
  val text: String,
  @ColumnInfo(name = "color")
  val color: Color,
  @ColumnInfo(name = "created_at")
  val createdAt: String,
  @ColumnInfo(name = "updated_at")
  val updatedAt: String,
  @ColumnInfo(name = "is_archived")
  val isArchived: Boolean,
  @ColumnInfo(name = "is_deleted")
  val isDeleted: Boolean
)
