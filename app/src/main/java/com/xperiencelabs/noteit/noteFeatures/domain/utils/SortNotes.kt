package com.xperiencelabs.noteit.noteFeatures.domain.utils

sealed class SortNotes{
    object Ascending:SortNotes()
    object Descending:SortNotes()
}
