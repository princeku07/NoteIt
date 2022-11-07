package com.xperiencelabs.noteit.noteFeatures.domain.use_case

import com.xperiencelabs.noteit.noteFeatures.domain.model.InvalidNoteException
import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.repository.NoteRepository

class AddNote (
    private val repository: NoteRepository
    ){
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
                throw InvalidNoteException("The title of note can't be empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("Content is required")
        }
        repository.insertNote(note)
    }
}