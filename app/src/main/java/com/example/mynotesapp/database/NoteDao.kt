package com.example.mynotesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    /* Inserts a new note into the table */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    /* Deletes a Note from the table */
    @Delete
    suspend fun delete(note:Note)

    /*Selects and returns all rows in the table, sorted by
      noteId in Descending order
     */
    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>
}