package com.example.marvelheroes.features.heroes.domain.repository

import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import com.example.marvelheroes.features.heroes.domain.model.HeroesList
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<CharactersResponseData>
    fun getCharactersById(id: String): Flow<CharactersResponseData>
}