package com.xperiencelabs.noteit.noteFeatures.presenter.notes

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xperiencelabs.noteit.R
import com.xperiencelabs.noteit.noteFeatures.presenter.notes.component.FilterSection
import com.xperiencelabs.noteit.noteFeatures.presenter.notes.component.NoteItem
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    //to show snack-bars
    val scaffoldState = rememberScaffoldState()
    /*Use rememberCoroutineScope()
    when you are using coroutines and need to cancel and relaunch the coroutine after an event
    */
     
    val scope = rememberCoroutineScope()
    /*scaffold allows us to implement a UI with the basic Material design layout structure.It provides slots
    for the most common top-level material components,such as TopAppBar,BottomAppBar,FloatingActionButton and Drawer*/
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

            },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                 Icon(imageVector = Icons.Default.Add, contentDescription = "New note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                        Text(text = "NoteIt",
                        style = MaterialTheme.typography.h4)
                    
                    IconButton(onClick = { 
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                            contentDescription ="Filter", tint = Color.Green )
                    }
                }
        }
        AnimatedVisibility(visible = state.isOrderSectionVisible,
        enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
                FilterSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    } )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.notes){note->
                NoteItem(note = note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }, onDeleteClick = {
                        viewModel.onEvent(NotesEvent.DeleteNote(note))
                        scope.launch {
                           val result= scaffoldState.snackbarHostState.showSnackbar(
                                "Note deleted",
                                "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed){
                                viewModel.onEvent(NotesEvent.RestoreNote)
                            }
                        }
                    })

                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}