package com.example.marvelheroes.features.heroes.data.datasource.rest

import com.example.marvelheroes.core.network.BaseApiClient
import com.example.marvelheroes.features.heroes.data.datasource.rest.interfaces.ICharactersApiClient

object CharactersApiClient : BaseApiClient<ICharactersApiClient>(ICharactersApiClient::class.java)