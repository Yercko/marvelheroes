package com.example.marvelheroes.di

import com.example.marvelheroes.features.heroes.data.datasource.rest.CharactersRestDataStore
import com.example.marvelheroes.features.heroes.data.repository.CharactersRepositoryImpl
import com.example.marvelheroes.features.heroes.domain.interactor.GetCharacterByIdInteractor
import com.example.marvelheroes.features.heroes.domain.interactor.GetCharactersInteractor
import com.example.marvelheroes.features.heroes.domain.repository.CharactersRepository
import com.example.marvelheroes.features.heroes.presentation.viewmodel.CharacterViewModel
import com.example.marvelheroes.features.heroes.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val charactersModules  = module{
    viewModel {
        CharactersViewModel(get())
    }
    viewModel {
        CharacterViewModel(get())
    }
    //region interactor
    single {
        GetCharactersInteractor(
            get()
        )
    }
    single {
        GetCharacterByIdInteractor(
            get()
        )
    }

    //region Repository
    single<CharactersRepository> {
        CharactersRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        CharactersRestDataStore()
    }
    //endregion
}

val modules = listOf(charactersModules)