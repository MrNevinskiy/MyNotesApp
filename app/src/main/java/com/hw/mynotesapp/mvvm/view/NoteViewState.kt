package com.hw.mynotesapp.mvvm.view

import com.hw.mynotesapp.mvvm.model.Note

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}