package com.example.create

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun CreateScreen(
  sharedTransitionScope: SharedTransitionScope,
  animatedVisibilityScope: AnimatedVisibilityScope,
  modifier: Modifier = Modifier,
  onBackButtonClick: () -> Unit
) {
  var title by remember {
    mutableStateOf("")
  }

  var note by remember {
    mutableStateOf("")
  }

  with(sharedTransitionScope) {
    Scaffold(modifier = modifier
      .fillMaxSize()
      .sharedElement(rememberSharedContentState(key = "floating"), animatedVisibilityScope = animatedVisibilityScope), topBar = {
      TopAppBar(title = {}, navigationIcon = {
        IconButton(onClick = onBackButtonClick) {
          Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back button", tint = Color.Unspecified)
        }
      })
    }, content = { paddingValues ->
      Column(modifier = Modifier.padding(paddingValues)) {
        
      }
    })
  }
}