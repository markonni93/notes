package com.thequicknotes.bin

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.thequicknotes.R
import com.thequicknotes.home.card.NoteCard
import com.thequicknotes.uicomponents.loading.LoadingScreen
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold
import com.thequicknotes.uicomponents.topbar.DefaultTopBar

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DeletedNotesScreen(onBackClicked: () -> Unit) {
  val viewModel: DeletedNotesScreenViewModel = hiltViewModel<DeletedNotesScreenViewModel>()
  val items = viewModel.items.collectAsLazyPagingItems()

  val isLoading by remember {
    derivedStateOf { items.loadState.refresh == LoadState.Loading }
  }

  BaseBottomSheetScaffold(scaffoldState = rememberBottomSheetScaffoldState(), topBar = {
    DefaultTopBar(R.string.deleted_notes_screen_title, actions = {
      // TODO implement more menu
    }, onNavigationIconClick = onBackClicked)
  }, content = {
    AnimatedContent(targetState = isLoading, label = "DeletedNotesScreen") { loading ->
      when (loading) {
        true -> {
          LoadingScreen()
        }

        false -> {
          Column {
            Text(
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
              text = stringResource(id = R.string.deleted_notes_information_text),
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.primary
            )

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
                    //onNoteClicked(id)
                  }, onMoreMenuClicked = { noteId ->
                    // showBottomSheet(noteId)
                  }, itemSelected = { id, isSelected ->
//          when (isSelected) {
//            true -> {
//              selectedItems.add(id)
//              onSelectedItemsChange(id)
//            }
//
//            else -> selectedItems.remove(id)
//          }
                  }, isSelectMode = false
                  )
                }
              })
            }
          }
        }
      }
    }
  })
}