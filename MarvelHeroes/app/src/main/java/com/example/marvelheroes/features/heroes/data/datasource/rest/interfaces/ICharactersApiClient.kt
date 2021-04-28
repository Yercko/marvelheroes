package com.example.marvelheroes.features.heroes.data.datasource.rest.interfaces
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.CharactersResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface ICharactersApiClient {
    @GET("/v1/public/characters")
    suspend fun getCharacters(): CharactersResponseData

    @GET("v1/public/characters/{id}")
    suspend fun getCharacterById(@Path("id") id:String): CharactersResponseData
}