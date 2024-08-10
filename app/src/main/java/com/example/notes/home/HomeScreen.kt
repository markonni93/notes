package com.example.notes.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose.AppTheme

@Composable
fun HomeScreen(modifier: Modifier) {
  val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()

  val items = viewModel.items.collectAsLazyPagingItems()

  val isScreenEmpty by remember { derivedStateOf { items.itemCount == 0 } }

  Box(modifier = modifier.fillMaxSize()) {
    when {
      isScreenEmpty -> EmptyHomeScreen(modifier = Modifier.align(Alignment.TopCenter))
      else -> {
        LazyColumn {
          this.items(count = items.itemCount, key = { index -> items[index]!!.id }, itemContent = { index ->
            val item = items[index]
            Text(text = item?.title ?: "No title", color = Color.Red)
            Text(text = item?.description ?: "No description", color = Color.Blue)
          })
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    HomeScreen(modifier = Modifier)
  }
}