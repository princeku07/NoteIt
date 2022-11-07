package com.xperiencelabs.noteit.noteFeatures.domain.utils

sealed class NoteOrder(val sortNotes: SortNotes) {
    class Title(sortNotes: SortNotes):NoteOrder(sortNotes)
    class Date(sortNotes: SortNotes):NoteOrder(sortNotes)
    class Color(sortNotes: SortNotes):NoteOrder(sortNotes)
}