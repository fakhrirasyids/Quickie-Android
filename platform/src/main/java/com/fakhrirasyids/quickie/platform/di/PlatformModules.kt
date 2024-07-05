package com.fakhrirasyids.quickie.platform.di

import com.fakhrirasyids.quickie.platform.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { HomeViewModel(get()) }
}