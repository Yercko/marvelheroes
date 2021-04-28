package com.example.marvelheroes.features.heroes.data.datasource.rest.response

data class DataResponse (
    val offset: String,
    val limit: String,
    val total: String,
    val count: String,
    val results: List<ResultResponse>
)