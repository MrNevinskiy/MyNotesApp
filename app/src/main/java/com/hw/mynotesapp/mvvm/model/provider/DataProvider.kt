package com.hw.mynotesapp.mvvm.model.provider

import androidx.lifecycle.LiveData
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.model.NoteResult


interface DataProvider {
    fun subscribeToAllNotes() : LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
    fun getNoteById(id: String) : LiveData<NoteResult>
}