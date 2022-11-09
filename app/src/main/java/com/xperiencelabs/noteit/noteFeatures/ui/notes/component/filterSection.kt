package com.xperiencelabs.noteit.noteFeatures.ui.notes.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xperiencelabs.noteit.noteFeatures.domain.utils.NoteOrder
import com.xperiencelabs.noteit.noteFeatures.domain.utils.SortNotes

@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(SortNotes.Descending),
    onOrderChange:(NoteOrder) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DefaultRadioButton(text = "Title",
                    select = noteOrder is NoteOrder.Title,
                    onSelect = {
                        onOrderChange(NoteOrder.Title(noteOrder.sortNotes))
                    })
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(text = "Date",
                    select = noteOrder is NoteOrder.Date,
                    onSelect = {
                        onOrderChange(NoteOrder.Date(noteOrder.sortNotes))
                    })
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(text = "Color",
                    select = noteOrder is NoteOrder.Color,
                    onSelect = {
                        onOrderChange(NoteOrder.Color(noteOrder.sortNotes))
                    })
                Spacer(modifier = Modifier.width(8.dp))
            }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = "Ascending",
                select = noteOrder.sortNotes is SortNotes.Ascending,
                onSelect = { onOrderChange(noteOrder.copy(SortNotes.Ascending)) })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Ascending",
                select = noteOrder.sortNotes is SortNotes.Ascending,
                onSelect = { onOrderChange(noteOrder.copy(SortNotes.Ascending)) })
        }

    }
}