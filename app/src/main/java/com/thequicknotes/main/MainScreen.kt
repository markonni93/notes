package com.thequicknotes.main

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.thequicknotes.R
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.home.empty.EmptyHomeScreen
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold
import com.thequicknotes.uicomponents.search.SearchField

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
  sharedContentStateKey: String,
  onNoteClicked: (Int) -> Unit,
  onCreateNoteClicked: () -> Unit,
  title: String?,
  note: String?,
  color: Color?
) {
  val animationData = LocalSharedTransitionLayoutData.current

  val viewModel: MainViewModel = hiltViewModel<MainViewModel>()

  val items = viewModel.items.collectAsLazyPagingItems()

  val isScreenEmpty by remember {
    derivedStateOf { items.itemCount == 0 }
  }

  var newTitle by remember {
    mutableStateOf(title)
  }

  var newNote by remember {
    mutableStateOf(note)
  }

  var newColor by remember {
    mutableStateOf(color)
  }

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberStandardBottomSheetState(
      initialValue = SheetValue.Hidden, skipHiddenState = false
    )
  )

  DisposableEffect(newTitle, newNote, newColor) {
    viewModel.insertNote(newTitle, newNote, newColor ?: Color.White)
    onDispose {
      newTitle = null
      newNote = null
      newColor = null
    }
  }

  val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

  BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, topBar = {
    TopAppBar(scrollBehavior = scrollBehavior, title = {
      SearchField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), onSearch = { query ->
        viewModel.searchNotes(query)
      })
    })
  }, content = {
    Scaffold(floatingActionButton = {
      with(animationData.animatedContentScope) {
        with(animationData.transitionLayout) {
          FloatingActionButton(modifier = Modifier
            .sharedBounds(
              animationData.transitionLayout.rememberSharedContentState(key = sharedContentStateKey), animationData.animatedContentScope
            )
            .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
            .animateEnterExit(enter = fadeIn() + slideInVertically { it }, exit = fadeOut() + slideOutVertically { it }), onClick = {
            onCreateNoteClicked()
          }) {
            Icon(painter = painterResource(id = R.drawable.create_note_icon), contentDescription = "Create icon", tint = Color.Unspecified)
          }
        }
      }
    }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
      if (isScreenEmpty) {
        EmptyHomeScreen(modifier = Modifier.padding(paddingValues))
      } else {
        HomeScreen(
          Modifier
            //.padding(paddingValues)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(), items, showBottomSheet = {
          }, onNoteClicked = { id ->
            onNoteClicked(id)
          }
        )
      }
    })
  })
}