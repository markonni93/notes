package com.thequicknotes.createnote

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.R
import com.thequicknotes.data.model.NoteColor
import com.thequicknotes.navigation.DataForAnimation
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData
import com.thequicknotes.uicomponents.topbar.DefaultTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateScreen(
  sharedContentStateKey: String, modifier: Modifier = Modifier, onBackPressed: (String, String, String) -> Unit
) {

  val animationData = LocalSharedTransitionLayoutData.current
  val backPressHandle = LocalOnBackPressedDispatcherOwner.current

  val keyboardController = LocalSoftwareKeyboardController.current
  val coroutineScope = rememberCoroutineScope()

  val sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Hidden, skipHiddenState = false)
  var shouldShowBottomSheet by remember {
    mutableStateOf(false)
  }

  var title by remember {
    mutableStateOf("")
  }

  var note by remember {
    mutableStateOf("")
  }

  var colorFrom by remember { mutableStateOf(Color.White) }
  var colorTo by remember { mutableStateOf(Color.White) }
  val color = remember { Animatable(colorTo) }

  BackHandler(enabled = true, onBack = {
    coroutineScope.launch {
      keyboardController?.hide()
      onBackPressed(title, note, colorTo.value.toString())
    }
  })

  LaunchedEffect(colorTo) {
    color.animateTo(colorFrom, animationSpec = tween(100))
    color.animateTo(colorTo, animationSpec = tween(100))
    colorFrom = colorTo
  }

  if (shouldShowBottomSheet) {
    ModalBottomSheet(onDismissRequest = {
      shouldShowBottomSheet = false
    }, sheetState = sheetState) {
      Text(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        text = "Choose template color",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
      )
      LazyRow {
        items(count = NoteColor.entries.size, itemContent = { int ->
          Surface(modifier = Modifier
            .clickable {
              colorTo = NoteColor.entries[int].color
            }
            .padding(12.dp)
            .size(48.dp), shape = CircleShape, color = NoteColor.entries[int].color, border = BorderStroke(1.dp, Color.Black), content = {})
        })
      }
    }
  }

  with(animationData.transitionLayout) {
    Scaffold(modifier = modifier
      .sharedBounds(rememberSharedContentState(key = sharedContentStateKey), animatedVisibilityScope = animationData.animatedContentScope),
      topBar = {
        DefaultTopBar(onNavigationIconClick = {
          backPressHandle?.onBackPressedDispatcher?.onBackPressed()
        }, actions = {
          IconButton(onClick = {
            shouldShowBottomSheet = true
          }) {
            Icon(painter = painterResource(id = R.drawable.more_icon), contentDescription = "More menu icon")
          }
        })
      },
      content = { paddingValue ->
        Column(modifier = Modifier
          .padding(paddingValue)
          .imePadding()
          .verticalScroll(rememberScrollState())) {
          TextField(value = title, onValueChange = {
            title = it
          }, textStyle = MaterialTheme.typography.headlineLarge, colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
          ), placeholder = {
            Text(text = "Title here", style = MaterialTheme.typography.headlineLarge)
          })

          Spacer(modifier = Modifier.padding(top = 8.dp))

          TextField(value = note, onValueChange = {
            note = it
          }, textStyle = MaterialTheme.typography.bodyMedium, colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
          ), placeholder = {
            Text(text = "Write your notes here", style = MaterialTheme.typography.bodyMedium)
          })
        }
      })
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCreateScreen() {
  SharedTransitionLayout {
    AnimatedContent(targetState = true) { show ->
      if (show) {
        CompositionLocalProvider(value = LocalSharedTransitionLayoutData provides DataForAnimation(transitionLayout = this@SharedTransitionLayout, animatedContentScope = this)) {
          CreateScreen("", Modifier, { _, _, _ -> })
        }
      }
    }
  }
}