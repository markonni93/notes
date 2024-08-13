package com.thequicknotes.data.uimodel

import androidx.compose.ui.graphics.Color

data class NoteUiModel(
  val id: Int,
  val title: String,
  val description: String,
  val color: Color
)
