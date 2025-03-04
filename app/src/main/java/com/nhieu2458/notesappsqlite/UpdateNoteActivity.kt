package com.nhieu2458.notesappsqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nhieu2458.notesappsqlite.databinding.ActivityUpdateNoteBinding


class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.editNoteTitle.setText(note.title)
        binding.editNoteDesc.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.editNoteTitle.text.toString()
            val newContent = binding.editNoteDesc.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }

    }
}