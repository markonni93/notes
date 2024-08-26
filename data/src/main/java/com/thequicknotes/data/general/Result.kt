package com.thequicknotes.data.general

sealed class Result<out T> {
  data class Success<out T>(val data: T) : Result<T>()
  data class Error(val exception: Exception) : Result<Nothing>()

  fun isSuccess(): Boolean = this is Success<T>

  fun isError(): Boolean = this is Error
}