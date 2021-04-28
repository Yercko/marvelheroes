package com.example.marvelheroes.features.heroes.data.datasource.rest.response

data class CharactersResponseData(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: DataResponse,
    val etag: String
)