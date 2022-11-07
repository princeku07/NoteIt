package com.xperiencelabs.noteit.noteFeatures.domain.use_case

import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.repository.NoteRepository
import com.xperiencelabs.noteit.noteFeatures.domain.utils.NoteOrder
import com.xperiencelabs.noteit.noteFeatures.domain.utils.SortNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNoteUseCase(private val repository: NoteRepository) {

    operator  fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(SortNotes.Descending)
    ):Flow<List<Note>>{
            return repository.getNotes().map { notes->
                            when(noteOrder.sortNotes){
                                is SortNotes.Ascending ->{
                                    when(noteOrder){
                                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                                        is NoteOrder.Color -> notes.sortedBy { it.color}
                                    }
                                }
                                is SortNotes.Descending ->{
                                    when(noteOrder){
                                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                                        is NoteOrder.Color -> notes.sortedByDescending { it.color}
                                    }
                                }
                            }
            }
    }

}