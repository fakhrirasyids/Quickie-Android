package com.freelanxx.quickie.game.quickie2048.di

import com.freelanxx.quickie.game.quickie2048.data.GameRepositoryImpl
import com.freelanxx.quickie.game.quickie2048.domain.interactor.GameInteractor
import com.freelanxx.quickie.game.quickie2048.domain.repository.GameRepository
import com.freelanxx.quickie.game.quickie2048.domain.usecase.GameUseCase
import com.freelanxx.quickie.game.quickie2048.ui.screen.GameScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModules = module {
    single<GameRepository> {GameRepositoryImpl()}
}

val useCaseModules = module {
    single<GameUseCase> { GameInteractor(get<GameRepository>()) }
}

val viewModelModules = module {
    viewModel { GameScreenViewModel(get<GameUseCase>()) }
}