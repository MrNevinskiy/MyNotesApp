package com.hw.mynotesapp.mvvm.view

import com.hw.mynotesapp.mvvm.view.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null)
    : BaseViewState<Boolean?>(authenticated, error)