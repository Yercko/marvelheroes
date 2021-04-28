package com.example.marvelheroes.features.heroes.data.datasource.rest.response

data class ResultResponse (
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<UrlResponse>,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val stories: Stories,
    val events: Comics,
    val series: Comics
)