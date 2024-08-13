package com.example.notes.home.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data.model.NoteColor
import com.example.data.uimodel.NoteUiModel

@Composable
fun NoteCard(item: NoteUiModel, onCardClicked: (Int) -> Unit) {
  Card(
    modifier = Modifier.heightIn(max = 300.dp),
    onClick = { onCardClicked(item.id) },
    border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
    colors = CardColors(containerColor = item.color, contentColor = Color.Unspecified, disabledContentColor = Color.Unspecified, disabledContainerColor = Color.Unspecified)
  ) {
    Text(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), text = item.title, style = MaterialTheme.typography.titleMedium)
    Text(modifier = Modifier.padding(horizontal = 12.dp), text = item.description, style = MaterialTheme.typography.bodyMedium, overflow = TextOverflow.Ellipsis)
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNoteCard() {
  NoteCard(
    onCardClicked = {},
    item = NoteUiModel(
      1,
      "Note title",
      description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sed sapien sollicitudin, condimentum velit ac, pulvinar dui. Nulla vehicula orci sit amet odio efficitur, non volutpat ante accumsan. Sed porttitor ut nisi in dignissim. Donec blandit commodo elit quis aliquam. Nunc dictum turpis a urna congue, congue eleifend ipsum sollicitudin. Aliquam vel mauris diam. Duis euismod elit et tincidunt congue. Phasellus tincidunt arcu et varius facilisis. In hac habitasse platea dictumst. Donec efficitur tristique suscipit. Phasellus mollis mollis lorem id elementum. Donec posuere tellus non mollis efficitur",
      color = NoteColor.YELLOW.color
    )
  )
}