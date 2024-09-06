package com.thequicknotes.data.general

sealed class UiState<out T> {
  data class Success<out T>(val data: T? = null) : UiState<T>()
  data class Error(val exception: Exception) : UiState<Nothing>()

  fun isSuccess(): Boolean = this is Success<T>

  fun isError(): Boolean = this is Error
}