package com.thequicknotes.archive

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArchivedNotesScreen() {

  val viewModel: ArchivedNotesScreenViewModel = hiltViewModel<ArchivedNotesScreenViewModel>()

  Text(text = "Archived notes screen")
}