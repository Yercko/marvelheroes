package com.example.marvelheroes.features.heroes.data.mapper

import com.example.marvelheroes.core.mapper.Mapper
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import com.example.marvelheroes.features.heroes.domain.model.HeroesList

class CharactersDataMapper : Mapper<CharactersResponseData, HeroesList> {
    override fun map(origin: CharactersResponseData) =
        HeroesList(
            items = origin.data.results
        )
}