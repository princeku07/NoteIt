package com.xperiencelabs.noteit.noteFeatures.domain.model


import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xperiencelabs.noteit.ui.theme.*

@Entity
data class Note(
    @PrimaryKey val id:Int? = null,
    val title:String,
    val content:String,
    val color:Int,
    val timestamp:Long
)
{
    companion object{
        val noteColors = listOf(BlueViolet,Yellow,Orange,Aqua,Brown,Cyan)
    }
}
class InvalidNoteException(message:String):Exception(message)
