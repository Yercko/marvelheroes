package com.example.marvelheroes.features.heroes.presentation.viewmodel.state

import com.example.marvelheroes.features.heroes.presentation.model.CharacterVM

sealed class  CharactersVS {
    class AddCharacter(val characterVM: CharacterVM):CharactersVS()
    class Error(val message:String?):CharactersVS()
    class ShowLoader(val showLoader:Boolean):CharactersVS()
}