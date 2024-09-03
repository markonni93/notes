package com.thequicknotes.data.utils

import com.thequicknotes.data.entities.NoteEntity
import com.thequicknotes.data.uimodel.NoteUiModel

fun NoteEntity.toUiModel(): NoteUiModel {
  return NoteUiModel(
    id = this.id!!,
    title = this.title,
    description = this.text,
    color = this.color
  )
}