package com.xperiencelabs.noteit.noteFeatures.data.repository

import com.xperiencelabs.noteit.noteFeatures.data.data_source.NoteDao
import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(private val dao: NoteDao) :NoteRepository{

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
       return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
      return  dao.deleteNote(note)
    }
}