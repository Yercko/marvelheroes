package com.example.marvelheroes.features.heroes.domain.model

import com.example.marvelheroes.features.heroes.data.datasource.rest.response.DataResponse
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.ResultResponse

data class HeroesList (
    val items:  List<ResultResponse>?
)