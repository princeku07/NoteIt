package com.xperiencelabs.noteit.noteFeatures.ui.edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.xperiencelabs.noteit.noteFeatures.domain.use_case.GetSingleNote
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewEditViewModel @Inject constructor(
    private  val GetSingleNote:GetSingleNote
):ViewModel() {

    private val _noteTitle = mutableStateOf("")
    val noteTitle:State<String> = _noteTitle

}