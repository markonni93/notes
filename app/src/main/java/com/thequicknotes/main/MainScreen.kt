package com.thequicknotes.main

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.SnackbarResult.Dismissed
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.thequicknotes.R
import com.thequicknotes.home.HomeScreen
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData
import com.thequicknotes.uicomponents.bottomsheets.NotesBottomSheet
import com.thequicknotes.uicomponents.drawer.NotesDrawerItem
import com.thequicknotes.uicomponents.drawer.NotesDrawerSheet
import com.thequicknotes.uicomponents.scaffold.BaseBottomSheetScaffold
import com.thequicknotes.uicomponents.search.SearchField
import com.thequicknotes.uicomponents.snackbar.ErrorSnackbar
import com.thequicknotes.uicomponents.snackbar.SuccessSnackbar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
  sharedContentStateKey: String,
  title: LiveData<String?>?,
  note: LiveData<String?>?,
  color: LiveData<String?>?,
  onNoteClicked: (Int) -> Unit,
  onCreateNoteClicked: () -> Unit,
  onDrawerItemClicked: (NotesDrawerItem) -> Unit
) {
  val animationData = LocalSharedTransitionLayoutData.current

  val viewModel: MainViewModel = hiltViewModel<MainViewModel>()

  val deletingNotesSuccess = viewModel.deletingNotesState.observeAsState()

  val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val coroutineScope = rememberCoroutineScope()

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberModalBottomSheetState()
  )

  val newTitle = title?.observeAsState()
  val newDescription = note?.observeAsState()
  val newColor = color?.observeAsState()

  LaunchedEffect(deletingNotesSuccess.value) {
    when (deletingNotesSuccess.value) {
      true -> coroutineScope.launch {
        val result = bottomSheetScaffoldState.snackbarHostState.showSnackbar(
          message = "Note moved to bin", actionLabel = "Undo", withDismissAction = true, duration = SnackbarDuration.Short
        )
        when (result) {
          Dismissed -> {
            // do nothing
          }

          ActionPerformed -> {
            viewModel.restoreNotesFromBin()
          }
        }
        viewModel.clearSnackbarStatus()
      }

      false -> coroutineScope.launch {
        bottomSheetScaffoldState.snackbarHostState.showSnackbar(
          message = "Deleting notes failed"
        )
        viewModel.clearSnackbarStatus()
      }

      null -> {
        // do nothing
      }
    }
  }

  LaunchedEffect(newTitle, newDescription, newColor) {
    viewModel.insertNote(newTitle?.value, newDescription?.value, newColor?.value)
  }

  ModalNavigationDrawer(drawerContent = {
    NotesDrawerSheet(onNavItemClicked = { navItem ->
      coroutineScope.launch {
        drawerState.close()
        if (!drawerState.isAnimationRunning) {
          onDrawerItemClicked(navItem)
        }
      }
    })
  }, drawerState = drawerState) {
    BaseBottomSheetScaffold(modifier = Modifier.fillMaxSize(), scaffoldState = bottomSheetScaffoldState, topBar = {
      TopAppBar(scrollBehavior = scrollBehavior, title = {
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
      NotesBottomSheet(onDeleteClicked = {
        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
        viewModel.moveNotesToBin()
      }, onArchiveCLicked = {
        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
        viewModel.archiveNote()
      }, onShareClicked = {
        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
      })
    }, snackbar = {
      SnackbarHost(hostState = bottomSheetScaffoldState.snackbarHostState) { data ->
        if (deletingNotesSuccess.value == true) {
          SuccessSnackbar(snackbarData = data)
        } else {
          ErrorSnackbar(snackbarData = data)
        }
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
      }, floatingActionButtonPosition = FabPosition.End, content = { _ ->
        HomeScreen(
          Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(), showBottomSheet = { id ->
            viewModel.addSelectedNotesId(id)
            coroutineScope.launch {
              bottomSheetScaffoldState.bottomSheetState.expand()
            }
          }, onNoteClicked = { id ->
            onNoteClicked(id)
          }, shouldShowBottomSheet = { shouldShowBottomSheet ->
            when (shouldShowBottomSheet) {
              true -> coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
              false -> coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
            }
          }, onSelectedItemsChange = { id ->
            viewModel.addSelectedNotesId(id)
          })
      })
    })
  }
}
