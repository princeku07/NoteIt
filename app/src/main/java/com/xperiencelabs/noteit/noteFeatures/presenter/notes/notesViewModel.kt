package com.xperiencelabs.noteit.noteFeatures.presenter.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import com.xperiencelabs.noteit.noteFeatures.domain.use_case.NoteUseCases
import com.xperiencelabs.noteit.noteFeatures.domain.utils.NoteOrder
import com.xperiencelabs.noteit.noteFeatures.domain.utils.SortNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
):ViewModel(){

    private val _state  = mutableStateOf(NotesState())
    val state:State<NotesState> = _state

    private var recentlyDeletedNotes:Note? = null
    private  var getNotesJob:Job?= null
    init {
        getNotes(NoteOrder.Date(SortNotes.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order ->{
                        if (state.value.noteOrder::class == event.noteOrder::class&&
                                state.value.noteOrder.sortNotes == event.noteOrder.sortNotes){
                            return
                        }
                getNotes(event.noteOrder)
            }
            is NotesEvent.ToggleOrderSection ->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }
            is NotesEvent.DeleteNote ->{

                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNotes = event.note

                }
            }
            is NotesEvent.RestoreNote ->{
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNotes?:return@launch)
                    recentlyDeletedNotes= null
                }

            }
        }


        }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
       getNotesJob= noteUseCases.getNotes(noteOrder)
            .onEach {notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)


    }


}
