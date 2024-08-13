package com.thequicknotes.data.converters

import androidx.compose.ui.graphics.Color
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class ColorConverter {
  @TypeConverter
  fun toLong(color: Color): Long = color.value.toLong()

  @TypeConverter
  fun toColor(value: Long): Color = Color(value.toULong())
}