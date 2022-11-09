package com.xperiencelabs.noteit.noteFeatures.ui.notes

import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.utils.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder):NotesEvent()
    data class DeleteNote(val note:Note):NotesEvent()
    object RestoreNote:NotesEvent()
    //we check order drop down
    object ToggleOrderSection:NotesEvent()

}