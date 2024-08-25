package com.thequicknotes.main

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.thequicknotes.R
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.home.empty.EmptyHomeScreen
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData
import com.thequicknotes.uicomponents.drawer.NotesDrawerSheet
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold
import com.thequicknotes.uicomponents.search.SearchField
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
  sharedContentStateKey: String, onNoteClicked: (Int) -> Unit, onCreateNoteClicked: () -> Unit, title: String?, note: String?, color: Color?
) {
  val animationData = LocalSharedTransitionLayoutData.current

  val viewModel: MainViewModel = hiltViewModel<MainViewModel>()

  val items = viewModel.items.collectAsLazyPagingItems()

  val isScreenEmpty by remember {
    derivedStateOf { items.itemCount == 0 }
  }

  val isLoading by remember {
    derivedStateOf { items.loadState.refresh == LoadState.Loading }
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
    bottomSheetState = rememberModalBottomSheetState()
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

  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val coroutineScope = rememberCoroutineScope()

  ModalNavigationDrawer(drawerContent = {
    NotesDrawerSheet(onArchiveClicked = {}, onBinClicked = {})
  }, drawerState = drawerState) {
    BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, topBar = {
      TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
          SearchField(modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth(), onSearch = { query ->
            viewModel.searchNotes(query)
          }, onMoreMenuClicked = {
            coroutineScope.launch {
              drawerState.open()
            }
          })
        })
    }, sheetContent = {
      // TODO Create UI Here
      IconButton(onClick = { }) {
        Icon(painter = painterResource(id = com.thequicknotes.uicomponents.R.drawable.delete_icon), contentDescription = "")
      }
    }, content = {
      Scaffold(floatingActionButton = {
        with(animationData.animatedContentScope) {
          with(animationData.transitionLayout) {
            FloatingActionButton(modifier = Modifier
              .sharedBounds(
                animationData.transitionLayout.rememberSharedContentState(key = sharedContentStateKey), animationData.animatedContentScope
              )
              .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
              .animateEnterExit(enter = fadeIn() + slideInVertically { it }, exit = fadeOut() + slideOutVertically { it }),
              containerColor = MaterialTheme.colorScheme.primaryContainer,
              contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
              onClick = {
                onCreateNoteClicked()
              }) {
              Icon(painter = painterResource(id = R.drawable.create_note_icon), contentDescription = "Create icon")
            }
          }
        }
      }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
        when {
          isLoading -> CircularProgressIndicator(modifier = Modifier.padding(paddingValues), color = MaterialTheme.colorScheme.primary)
          isScreenEmpty -> EmptyHomeScreen(modifier = Modifier.padding(paddingValues))
          else -> HomeScreen(
            Modifier
              .nestedScroll(scrollBehavior.nestedScrollConnection)
              .fillMaxSize(), items, showBottomSheet = {}, onNoteClicked = { id ->
              onNoteClicked(id)
            }, expandBottomSheet = { selectedItems ->
              Timber.d("MARKO selected items is not empty ${selectedItems.isNotEmpty()}")
              when (selectedItems.isNotEmpty()) {
                true -> coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                false -> coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
              }
            })
        }
      })
    })
  }
}