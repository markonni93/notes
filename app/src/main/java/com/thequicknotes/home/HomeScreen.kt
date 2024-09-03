package com.thequicknotes.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.thequicknotes.home.card.NoteCard
import com.thequicknotes.home.empty.EmptyHomeScreen

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  modifier: Modifier,
  showBottomSheet: (Int) -> Unit,
  onNoteClicked: (Int) -> Unit,
  shouldShowBottomSheet: (Boolean) -> Unit,
  onSelectedItemsChange: (Int) -> Unit
) {

  val viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
  val items = viewModel.items.collectAsLazyPagingItems()

  val pullToRefreshState = rememberPullToRefreshState()

  val isScreenEmpty by remember {
    derivedStateOf { items.itemCount == 0 }
  }

  val isLoading by remember {
    derivedStateOf { items.loadState.refresh == LoadState.Loading }
  }

  var isRefreshing by remember { mutableStateOf(false) }

  val selectedItems = remember {
    mutableStateListOf<Int>()
  }

  val isSelectMode by remember {
    derivedStateOf { selectedItems.isNotEmpty() }
  }

  LaunchedEffect(selectedItems.size) {
    shouldShowBottomSheet(selectedItems.isNotEmpty())
  }

  PullToRefreshBox(modifier = modifier,
    isRefreshing = isLoading || isRefreshing,
    onRefresh = {
      isRefreshing = true
      items.refresh()
      isRefreshing = false
    },
    state = pullToRefreshState,
    content = {
      when {
        isScreenEmpty -> EmptyHomeScreen(modifier = Modifier.fillMaxSize())
        else ->
          LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
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
    })
}