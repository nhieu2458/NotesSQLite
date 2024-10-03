package com.nhieu2458.notesappsqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nhieu2458.notesappsqlite.databinding.ActivityAddNoteBinding
import com.nhieu2458.notesappsqlite.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db  = NotesDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.addNoteTitle.text.toString()
            val content = binding.addNoteDesc.text.toString()
            val note = Note(0, title, content)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }

    }
}