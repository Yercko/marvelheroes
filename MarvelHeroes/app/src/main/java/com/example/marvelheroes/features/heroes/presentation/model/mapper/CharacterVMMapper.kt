package com.example.marvelheroes.features.heroes.presentation.model.mapper

import com.example.marvelheroes.core.mapper.Mapper
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import com.example.marvelheroes.features.heroes.domain.model.HeroesList
import com.example.marvelheroes.features.heroes.presentation.model.CharacterVM

class CharacterVMMapper  : Mapper<CharactersResponseData, CharacterVM> {
    override fun map(origin: CharactersResponseData) =
        CharacterVM(
            items = origin.data.results
        )
}