package com.example.marvelheroes.features.heroes.presentation.model

import com.example.marvelheroes.features.heroes.data.datasource.rest.response.ResultResponse

data class CharacterVM(
    val items: List<ResultResponse>?
)