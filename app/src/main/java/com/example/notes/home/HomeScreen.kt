package com.example.notes.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose.AppTheme
import com.example.notes.home.card.NoteCard
import com.example.notes.home.empty.EmptyHomeScreen

@Composable
fun HomeScreen(modifier: Modifier) {
  val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()

  val items = viewModel.items.collectAsLazyPagingItems()

  val isScreenEmpty by remember { derivedStateOf { items.itemCount == 0 } }

  Box(modifier = modifier.fillMaxSize()) {
    when {
      isScreenEmpty -> EmptyHomeScreen(modifier = Modifier.align(Alignment.TopCenter))
      else -> {
        LazyVerticalStaggeredGrid(
          columns = StaggeredGridCells.Fixed(2),
          contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
          verticalItemSpacing = 8.dp,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(count = items.itemCount, key = { index -> items[index]!!.id }, itemContent = { index ->
            val item = items[index]
            item?.let {
              NoteCard(item = it, onCardClicked = { _ -> })
            }
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