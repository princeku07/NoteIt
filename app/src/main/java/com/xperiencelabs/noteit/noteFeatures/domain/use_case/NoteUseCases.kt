package com.xperiencelabs.noteit.noteFeatures.domain.use_case

data class NoteUseCases(
    val getNotes: GetNoteUseCase,
    val deleteNote: DeleteNote,
    val addNote:AddNote
)