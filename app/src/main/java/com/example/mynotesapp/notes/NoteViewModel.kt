package com.example.mynotesapp.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.database.Note
import com.example.mynotesapp.database.NoteDao
import com.example.mynotesapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val noteDao: NoteDao,
                    application: Application): AndroidViewModel(application) {

        val allNotes: LiveData<List<Note>>
        private val repository: NoteRepository

        init {
            repository = NoteRepository(noteDao)
            allNotes = repository.allNotes
        }

        fun deleteNote(note:Note){

            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(note)
            }
        }

        private fun insertNote(note: Note){

            viewModelScope.launch(Dispatchers.IO) {
                repository.insert(note)
            }
        }

        fun createNote(text: String){

            if(text.isNotEmpty()){
                this.insertNote(Note(text))
            }
        }

}