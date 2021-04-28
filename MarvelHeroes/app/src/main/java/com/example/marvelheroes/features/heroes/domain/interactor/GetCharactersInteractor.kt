package com.example.marvelheroes.features.heroes.domain.interactor

import com.example.marvelheroes.core.interactor.Interactor
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import com.example.marvelheroes.features.heroes.domain.model.HeroesList
import com.example.marvelheroes.features.heroes.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersInteractor(
    private val characterRepository: CharactersRepository
) : Interactor<Interactor.None, Flow<CharactersResponseData>> {

    override fun execute(params: Interactor.None): Flow<CharactersResponseData> {
        return characterRepository.getCharacters()
    }
}