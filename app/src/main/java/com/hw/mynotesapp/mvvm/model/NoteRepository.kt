package com.hw.mynotesapp.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hw.mynotesapp.mvvm.model.provider.DataProvider
import com.hw.mynotesapp.mvvm.model.provider.FirestoreProvider
import java.util.*

object NoteRepository {

    private val dataProvider: DataProvider = FirestoreProvider()

    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
}