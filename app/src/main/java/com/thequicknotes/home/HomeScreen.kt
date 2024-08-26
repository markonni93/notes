package com.thequicknotes.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.thequicknotes.data.uimodel.NoteUiModel
import com.thequicknotes.home.card.NoteCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
  modifier: Modifier,
  items: LazyPagingItems<NoteUiModel>,
  showBottomSheet: (Int) -> Unit,
  onNoteClicked: (Int) -> Unit,
  shouldShowBottomSheet: (Boolean) -> Unit,
  onSelectedItemsChange: (Int) -> Unit
) {

  val selectedItems = remember {
    mutableStateListOf<Int>()
  }

  val isSelectMode by remember {
    derivedStateOf { selectedItems.isNotEmpty() }
  }

  LaunchedEffect(selectedItems.size) {
    shouldShowBottomSheet(selectedItems.isNotEmpty())
  }

  Column(modifier = modifier) {
    LazyVerticalStaggeredGrid(
      columns = StaggeredGridCells.Fixed(2),
      contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
      verticalItemSpacing = 8.dp,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(count = items.itemCount, key = { index -> items[index]!!.id }, itemContent = { index ->
        val item = items[index]
        item?.let {
          NoteCard(modifier = Modifier.animateItemPlacement(), item = it, onCardClicked = { id ->
            onNoteClicked(id)
          }, onMoreMenuClicked = { noteId ->
            showBottomSheet(noteId)
          }, itemSelected = { id, isSelected ->
            when (isSelected) {
              true -> {
                selectedItems.add(id)
                onSelectedItemsChange(id)
              }

              else -> selectedItems.remove(id)
            }
          }, isSelectMode = isSelectMode
          )
        }
      })
    }
  }
}