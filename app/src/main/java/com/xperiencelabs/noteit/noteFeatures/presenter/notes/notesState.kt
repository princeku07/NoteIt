package com.xperiencelabs.noteit.noteFeatures.presenter.notes

import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.utils.NoteOrder
import com.xperiencelabs.noteit.noteFeatures.domain.utils.SortNotes

data class NotesState (
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(sortNotes = SortNotes.Descending),

    val isOrderSectionVisible:Boolean = false
        )