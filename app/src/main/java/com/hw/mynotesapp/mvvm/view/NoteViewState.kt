package com.hw.mynotesapp.mvvm.view

import com.hw.mynotesapp.mvvm.model.Note

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)