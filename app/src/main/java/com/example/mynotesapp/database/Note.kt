package com.example.mynotesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "note_text")
    var text:String
){
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L
}