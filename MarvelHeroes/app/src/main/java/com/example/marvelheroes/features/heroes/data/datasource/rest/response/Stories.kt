package com.example.marvelheroes.features.heroes.data.datasource.rest.response

data class Stories (
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<StoriesItem>
)