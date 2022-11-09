package com.xperiencelabs.noteit.noteFeatures.domain.use_case

import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.repository.NoteRepository

class GetSingleNote(private val repository: NoteRepository) {

    suspend  operator fun invoke(id:Int): Note?{
                return repository.getNoteById(id)
    }
}