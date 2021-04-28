package com.example.marvelheroes.features.heroes.data.repository

import com.example.marvelheroes.features.heroes.data.datasource.rest.CharactersRestDataStore
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import com.example.marvelheroes.features.heroes.data.mapper.CharactersDataMapper
import com.example.marvelheroes.features.heroes.domain.model.HeroesList
import com.example.marvelheroes.features.heroes.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRepositoryImpl (
    private val characterRestDataStore: CharactersRestDataStore
) : CharactersRepository {

    private val mCharacterDataMapper by lazy { CharactersDataMapper() }

    override fun getCharacters(): Flow<CharactersResponseData> =
        characterRestDataStore.getCharacters()

    override fun getCharactersById(id: String): Flow<CharactersResponseData> =
        characterRestDataStore.getCharacterById(id = id)
}
