package com.nhieu2458.notesappsqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<Note>, context: Context) :
    RecyclerView.Adapter<NotesAdapter.Viewholder>() {
        private val db: NotesDatabaseHelper = NotesDatabaseHelper(context)
    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateBtn)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.Viewholder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text= note.title
        holder.contentTextView.text = note.content

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newNote: List<Note>){
        notes = newNote
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = notes.size
}