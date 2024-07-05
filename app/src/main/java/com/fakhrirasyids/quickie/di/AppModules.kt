package com.fakhrirasyids.quickie.di

import com.fakhrirasyids.quickie.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(get()) }
}