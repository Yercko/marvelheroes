package com.example.marvelheroes.features.heroes.domain.interactor

import com.example.marvelheroes.core.interactor.Interactor
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import com.example.marvelheroes.features.heroes.domain.model.HeroesList
import com.example.marvelheroes.features.heroes.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterByIdInteractor(
    private val heroesRepository: CharactersRepository
) : Interactor<GetCharacterByIdInteractor.Params, Flow<CharactersResponseData>> {

    override fun execute(params: Params): Flow<CharactersResponseData> {
        return heroesRepository.getCharactersById(id = params.id)
    }

    data class Params (val id: String)
}