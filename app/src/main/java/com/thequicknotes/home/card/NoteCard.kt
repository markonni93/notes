package com.thequicknotes.home.card

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thequicknotes.R
import com.thequicknotes.data.model.NoteColor
import com.thequicknotes.data.uimodel.NoteUiModel
import com.thequicknotes.navigation.LocalSharedTransitionLayoutData

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
  modifier: Modifier = Modifier,
  item: NoteUiModel,
  isSelectMode: Boolean,
  onCardClicked: (Int) -> Unit,
  onMoreMenuClicked: (Int) -> Unit,
  itemSelected: (Int, Boolean) -> Unit
) {

  val animationData = LocalSharedTransitionLayoutData.current

  var isSelected by remember {
    mutableStateOf(false)
  }

  val transition = updateTransition(isSelected, label = "selected state")
  val borderColor by transition.animateColor(label = "border color") { selected ->
    if (selected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
  }
  val borderStroke by transition.animateDp(label = "elevation") { selected ->
    if (selected) 3.dp else 1.dp
  }

  with(animationData.transitionLayout) {
    Card(
      modifier = modifier
        .heightIn(max = 300.dp)
        .sharedBounds(
          rememberSharedContentState(key = "details_${item.id}"),
          animatedVisibilityScope = animationData.animatedContentScope,
          resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
        )
        .combinedClickable(onClick = {
          if (isSelectMode) {
            isSelected = !isSelected
            itemSelected(item.id, isSelected)
          } else {
            onCardClicked(item.id)
          }
        }, onLongClick = {
          isSelected = true
          itemSelected(item.id, isSelected)
        }),
      border = BorderStroke(borderStroke, color = borderColor),
      colors = CardColors(containerColor = item.color, contentColor = Color.Unspecified, disabledContentColor = Color.Unspecified, disabledContainerColor = Color.Unspecified)
    ) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(
          modifier = Modifier
            .sharedElement(rememberSharedContentState(key = "text_details_${item.id}"), animatedVisibilityScope = animationData.animatedContentScope)
            .skipToLookaheadSize()
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth(0.8f),
          text = item.title,
          style = MaterialTheme.typography.bodyLarge,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = { onMoreMenuClicked(item.id) }) {
          Icon(painter = painterResource(id = R.drawable.more_icon), contentDescription = "More icon")
        }
      }
      Text(
        modifier = Modifier
          .sharedElement(rememberSharedContentState(key = "description_details_${item.id}"), animatedVisibilityScope = animationData.animatedContentScope)
          .skipToLookaheadSize()
          .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
        text = item.description,
        style = MaterialTheme.typography.bodyMedium,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNoteCard() {
  NoteCard(onCardClicked = {}, onMoreMenuClicked = {}, item = NoteUiModel(
    1,
    "Note title ",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sed sapien sollicitudin, condimentum velit ac, pulvinar dui. Nulla vehicula orci sit amet odio efficitur, non volutpat ante accumsan. Sed porttitor ut nisi in dignissim. Donec blandit commodo elit quis aliquam. Nunc dictum turpis a urna congue, congue eleifend ipsum sollicitudin. Aliquam vel mauris diam. Duis euismod elit et tincidunt congue. Phasellus tincidunt arcu et varius facilisis. In hac habitasse platea dictumst. Donec efficitur tristique suscipit. Phasellus mollis mollis lorem id elementum. Donec posuere tellus non mollis efficitur",
    color = NoteColor.LIGHT_YELLOW.color
  ), itemSelected = { _, _ -> },
    isSelectMode = false
  )
}