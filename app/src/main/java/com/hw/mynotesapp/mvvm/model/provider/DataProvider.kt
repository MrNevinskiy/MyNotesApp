package com.hw.mynotesapp.mvvm.model.provider

import androidx.lifecycle.LiveData
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.model.NoteResult
import com.hw.mynotesapp.mvvm.model.User


interface DataProvider {
    fun getCurrentUser() : LiveData<User?>
    fun subscribeToAllNotes() : LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
    fun getNoteById(id: String) : LiveData<NoteResult>
}