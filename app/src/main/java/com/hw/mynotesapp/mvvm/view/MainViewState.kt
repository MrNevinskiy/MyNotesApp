package com.hw.mynotesapp.mvvm.view

import com.hw.mynotesapp.mvvm.model.Note

class MainViewState(val note: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(note, error)