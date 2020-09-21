package com.hw.mynotesapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hw.mynotesapp.mvvm.view.BaseViewState

open class BaseViewModel<T, S : BaseViewState<T>> : ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()
    open fun getViewState(): LiveData<S> = viewStateLiveData
}