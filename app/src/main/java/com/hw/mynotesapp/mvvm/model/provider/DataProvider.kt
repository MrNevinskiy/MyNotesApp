package com.hw.mynotesapp.mvvm.model.provider


import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.model.NoteResult
import com.hw.mynotesapp.mvvm.model.User
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {
    fun subscribeToAllNotes(): ReceiveChannel<NoteResult>

    suspend fun getCurrentUser(): User?
    suspend fun saveNote(note: Note): Note
    suspend fun getNoteById(id: String): Note?
    suspend fun deleteNote(id: String)
}