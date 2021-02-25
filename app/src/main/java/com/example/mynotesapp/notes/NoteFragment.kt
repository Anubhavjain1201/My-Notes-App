package com.example.mynotesapp.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.R
import com.example.mynotesapp.database.Note
import com.example.mynotesapp.database.NoteDatabase
import com.example.mynotesapp.databinding.NotesFragmentBinding

class NoteFragment: Fragment(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: NotesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val noteDao = NoteDatabase.getDatabase(application).noteDao()

        val viewModelFactory = NoteViewModelFactory(noteDao, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        binding.noteViewModel = viewModel

        val adapter = NoteAdapter(this)
        binding.noteList.adapter = adapter

        viewModel.allNotes.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.addButton.setOnClickListener {

            val noteText = binding.input.text.toString()
            binding.input.text.clear()
            viewModel.createNote(noteText)
        }

        return binding.root
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(context, "${note.text} has been Deleted", Toast.LENGTH_LONG).show()
    }
}