package com.example.mynotesapp.notes

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.database.Note
import com.example.mynotesapp.databinding.ListItemNoteBinding

class NoteAdapter(private val listener: INotesRVAdapter): ListAdapter<Note, NoteAdapter.ViewHolder>(NoteDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(layoutInflater, parent, false)

        val viewHolder = ViewHolder(binding)

        binding.deleteButton.setOnClickListener{
            listener.onItemClicked(getItem(viewHolder.adapterPosition))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder constructor(private val binding: ListItemNoteBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Note){
            binding.noteText.text = item.text
        }

    }
}

class NoteDiffCallback: DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}