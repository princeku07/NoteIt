package com.xperiencelabs.noteit.noteFeatures.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xperiencelabs.noteit.noteFeatures.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    //this is not a suspend function because home screen need to get flow of notes created
    @Query("Select * from note")
    fun getNotes():Flow<List<Note>>

    @Query("Select * from note where id= :id")
    suspend fun getNoteById(id:Int):Note?

    //if note of id is already present then it will update it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)


}