package com.hw.mynotesapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hw.mynotesapp.mvvm.model.NoteRepository
import com.hw.mynotesapp.mvvm.view.MainViewState

class MainViewModel() : ViewModel() {

    private val viewStateLiveData = MutableLiveData<MainViewState>()

    init {
        NoteRepository.getNote().observeForever{
           viewStateLiveData.value = viewStateLiveData.value?.copy(note = it) ?: MainViewState(it)
        }
    }

    fun getViewState(): LiveData<MainViewState> = viewStateLiveData
}