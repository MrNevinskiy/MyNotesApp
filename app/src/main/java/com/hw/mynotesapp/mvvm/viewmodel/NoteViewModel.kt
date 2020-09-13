package com.hw.mynotesapp.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.model.NoteRepository

class NoteViewModel(private val repository: NoteRepository = NoteRepository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}
