package com.hw.mynotesapp.mvvm.viewmodel

import com.hw.mynotesapp.mvvm.model.NotesRepository
import com.hw.mynotesapp.mvvm.model.errors.NoAuthExceptions
import com.hw.mynotesapp.mvvm.view.SplashViewState
import kotlinx.coroutines.launch

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?>() {

    fun requestUser() = launch {
        notesRepository.getCurrentUser()?.let {
            setData(true)
        } ?: setError(NoAuthExceptions())
    }

}