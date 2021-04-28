package com.example.marvelheroes.features.heroes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.core.interactor.Interactor
import com.example.marvelheroes.core.platform.BaseViewModel
import com.example.marvelheroes.core.utils.io
import com.example.marvelheroes.core.utils.ui
import com.example.marvelheroes.features.heroes.domain.interactor.GetCharactersInteractor
import com.example.marvelheroes.features.heroes.presentation.model.mapper.CharacterVMMapper
import com.example.marvelheroes.features.heroes.presentation.viewmodel.state.CharactersVS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CharactersViewModel (
    private val getCharacterInteractor: GetCharactersInteractor
) : BaseViewModel() {

    val viewState: LiveData<CharactersVS> get() = mViewState
    private val mViewState = MutableLiveData<CharactersVS>()

    private val mCharacterVMMapper by lazy { CharacterVMMapper() }

    fun getCharacters() {
        viewModelScope.launch {
            mViewState.value = CharactersVS.ShowLoader(true)
            try {
                io {
                    getCharacterInteractor.execute(Interactor.None)
                        .collect {
                            ui {
                                mViewState.value = CharactersVS.AddCharacter(mCharacterVMMapper.map(it))
                            }
                        }
                }
            } catch (e: Exception) {
                ui { mViewState.value = CharactersVS.Error(e.message) }
            }
            mViewState.value = CharactersVS.ShowLoader(false)
        }
    }
}