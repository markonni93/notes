package com.thequicknotes.create

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.data.model.NoteColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun CreateScreen(
  sharedTransitionScope: SharedTransitionScope,
  animatedVisibilityScope: AnimatedVisibilityScope,
  modifier: Modifier = Modifier,
  onBackButtonClick: (title: String?, text: String?, color: Color) -> Unit
) {

  var title by remember {
    mutableStateOf("")
  }

  var note by remember {
    mutableStateOf("")
  }

  var colorFrom by remember { mutableStateOf(Color.White) }
  var colorTo by remember { mutableStateOf(Color.White) }

  val color = remember { Animatable(colorTo) }
  LaunchedEffect(colorTo) {
    color.animateTo(colorFrom, animationSpec = tween(100))
    color.animateTo(colorTo, animationSpec = tween(100))
    colorFrom = colorTo
  }

  with(sharedTransitionScope) {
    Scaffold(modifier = modifier
      .sharedElement(rememberSharedContentState(key = "floating"), animatedVisibilityScope = animatedVisibilityScope)
      .fillMaxSize(), containerColor = color.value, topBar = {
      TopAppBar(
        title = {},
        navigationIcon = {
          IconButton(onClick = { onBackButtonClick(title, note, colorTo) }) {
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back button", tint = Color.Unspecified)
          }
        },
      )
    }, content = { paddingValue ->
      Column(modifier = Modifier.padding(paddingValue)) {
        TextField(value = title, onValueChange = {
          title = it
        }, textStyle = MaterialTheme.typography.headlineLarge, colors = TextFieldDefaults.colors(
          focusedContainerColor = Color.Transparent,
          unfocusedContainerColor = Color.Transparent,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent
        ), placeholder = {
          Text(text = "Title here", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.surfaceDim)
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
          Text(text = "Write your notes here", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.surfaceDim)
        })

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
    })
  }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun PreviewCreateScreen() {
  SharedTransitionLayout {
    AnimatedContent(targetState = true) { targetState ->
      if (targetState) {
        CreateScreen(animatedVisibilityScope = this@AnimatedContent, sharedTransitionScope = this@SharedTransitionLayout, modifier = Modifier, onBackButtonClick = { _, _, _ -> })
      }
    }
  }
}