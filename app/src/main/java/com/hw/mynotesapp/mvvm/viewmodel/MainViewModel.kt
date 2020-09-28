package com.hw.mynotesapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.model.NoteRepository
import com.hw.mynotesapp.mvvm.model.NoteResult
import com.hw.mynotesapp.mvvm.view.MainViewState

class MainViewModel(notesRepository: NoteRepository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer<NoteResult> { result ->
        result ?: return@Observer
        when(result){
            is NoteResult.Success<*> ->  viewStateLiveData.value = MainViewState(note = result.data as? List<Note>)
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    private val repositoryNotes = notesRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever (notesObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }
}