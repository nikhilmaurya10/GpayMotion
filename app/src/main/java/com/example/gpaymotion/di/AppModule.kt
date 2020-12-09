package com.example.gpaymotion.di

import com.example.gpaymotion.data.ContactsRepository
import com.example.gpaymotion.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val appModule = module {

    single { ContactsRepository() }
    viewModel {
        MainActivityViewModel(get())
    }

}