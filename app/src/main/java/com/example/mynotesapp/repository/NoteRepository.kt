package com.example.mynotesapp.repository

import androidx.lifecycle.LiveData
import com.example.mynotesapp.database.Note
import com.example.mynotesapp.database.NoteDao

class NoteRepository(private val noteDao: NoteDao){

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}