package com.thequicknotes.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.thequicknotes.data.uimodel.NoteUiModel
import com.thequicknotes.home.card.NoteCard
import com.thequicknotes.home.empty.EmptyHomeScreen
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData
import com.thequicknotes.navigation.NOTE_DETAILS_NAVIGATION_ROUTE
import com.thequicknotes.uicomponents.search.SearchField
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
  modifier: Modifier, items: LazyPagingItems<NoteUiModel>,
  showBottomSheet: (Int) -> Unit,
  searchNotes: (String) -> Unit,
  navController: NavController
) {
  val animationData = LocalSharedTransitionLayoutData.current
  val isScreenEmpty by remember { derivedStateOf { items.itemCount == 0 } }

  Timber.d("MARKO Home screen created")

  Box(
    modifier = modifier
      .fillMaxSize()
  ) {
    when {
      isScreenEmpty -> EmptyHomeScreen(modifier = Modifier.align(Alignment.TopCenter))
      else -> {
        Column {
          SearchField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp), onSearch = { query ->
            searchNotes(query)
          })
          LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(count = items.itemCount, key = { index -> items[index]!!.id }, itemContent = { index ->
              val item = items[index]
              item?.let {
                with(animationData.transitionLayout) {
                  NoteCard(modifier = Modifier
                    .sharedElement(rememberSharedContentState(key = "details_${item.id}"), animatedVisibilityScope = animationData.animatedContentScope)
                    .animateItemPlacement(),
                    item = it,
                    onCardClicked = { _ ->
                      navController.navigate("$NOTE_DETAILS_NAVIGATION_ROUTE/${item.id}")
                    },
                    onMoreMenuClicked = { noteId ->
                      showBottomSheet(noteId)
                    })
                }
              }
            })
          }
        }
      }
    }
  }
}