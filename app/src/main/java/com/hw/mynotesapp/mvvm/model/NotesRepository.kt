package com.hw.mynotesapp.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hw.mynotesapp.mvvm.model.provider.DataProvider
import com.hw.mynotesapp.mvvm.model.provider.FirestoreProvider
import java.util.*

class NotesRepository(val dataProvider: DataProvider) {
    fun getCurrentUser() = dataProvider.getCurrentUser()
    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun deleteNote(id: String) = dataProvider.deleteNote(id)
}