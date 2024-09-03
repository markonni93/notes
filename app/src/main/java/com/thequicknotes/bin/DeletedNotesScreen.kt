package com.thequicknotes.bin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DeletedNotesScreen() {
  val viewModel: DeletedNotesScreenViewModel = hiltViewModel<DeletedNotesScreenViewModel>()

  Text(text = "Deleted notes screen")
}