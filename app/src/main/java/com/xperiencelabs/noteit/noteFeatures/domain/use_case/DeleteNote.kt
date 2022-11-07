package com.xperiencelabs.noteit.noteFeatures.domain.use_case

import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note:Note){
        repository.deleteNote(note)
    }

}