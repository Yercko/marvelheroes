package com.example.marvelheroes.features.heroes.presentation.viewmodel.state

import com.example.marvelheroes.features.heroes.presentation.model.CharacterVM

sealed class CharacterVS {
    class AddCharacter(val characterVM: CharacterVM):CharacterVS()
    class Error(val message:String?):CharacterVS()
    class ShowLoader(val showLoader:Boolean):CharacterVS()
}