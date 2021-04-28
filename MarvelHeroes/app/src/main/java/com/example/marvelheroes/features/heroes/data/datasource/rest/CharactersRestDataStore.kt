package com.example.marvelheroes.features.heroes.data.datasource.rest

import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRestDataStore {
    fun getCharacters(): Flow<CharactersResponseData> = flow {
        emit(CharactersApiClient.getApiClient().getCharacters())
    }

    fun getCharacterById(id: String): Flow<CharactersResponseData> = flow {
        emit(CharactersApiClient.getApiClient().getCharacterById(id = id))
    }

}