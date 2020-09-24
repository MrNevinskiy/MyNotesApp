package com.hw.mynotesapp.mvvm.viewmodel

import com.hw.mynotesapp.mvvm.model.NoteRepository
import com.hw.mynotesapp.mvvm.model.errors.NoAuthExceptions
import com.hw.mynotesapp.mvvm.view.SplashViewState

class SplashViewModel() : BaseViewModel<Boolean?, SplashViewState>(){
    fun requestUser(){
        NoteRepository.getCurrentUser().observeForever{
            viewStateLiveData.value = if(it != null){
                SplashViewState(authenticated = true)
            }else{
                SplashViewState(error = NoAuthExceptions())
            }
        }
    }
}