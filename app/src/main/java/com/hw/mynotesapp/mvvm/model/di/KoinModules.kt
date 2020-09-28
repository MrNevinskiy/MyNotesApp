package com.hw.mynotesapp.mvvm.model.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hw.mynotesapp.mvvm.model.NoteRepository
import com.hw.mynotesapp.mvvm.model.provider.DataProvider
import com.hw.mynotesapp.mvvm.model.provider.FirestoreProvider
import com.hw.mynotesapp.mvvm.viewmodel.MainViewModel
import com.hw.mynotesapp.mvvm.viewmodel.NoteViewModel
import com.hw.mynotesapp.mvvm.viewmodel.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single<DataProvider> { FirestoreProvider(get(), get()) }
    single { NoteRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}