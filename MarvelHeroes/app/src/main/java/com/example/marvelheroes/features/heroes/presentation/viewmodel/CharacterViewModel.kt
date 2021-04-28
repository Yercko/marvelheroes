package com.example.marvelheroes.features.heroes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.core.interactor.Interactor
import com.example.marvelheroes.core.platform.BaseViewModel
import com.example.marvelheroes.core.utils.io
import com.example.marvelheroes.core.utils.ui
import com.example.marvelheroes.features.heroes.domain.interactor.GetCharacterByIdInteractor
import com.example.marvelheroes.features.heroes.presentation.model.mapper.CharacterVMMapper
import com.example.marvelheroes.features.heroes.presentation.viewmodel.state.CharacterVS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CharacterViewModel (
    private val getCharacterByIdInteractor: GetCharacterByIdInteractor
) : BaseViewModel() {

    val viewState: LiveData<CharacterVS> get() = mViewState
    private val mViewState = MutableLiveData<CharacterVS>()

    private val mCharacterVMMapper by lazy { CharacterVMMapper() }

    fun getCharacterByID(id: String) {
        viewModelScope.launch {
            mViewState.value = CharacterVS.ShowLoader(true)
            try {
                io {
                    getCharacterByIdInteractor.execute(GetCharacterByIdInteractor.Params(id = id))
                        .collect {
                            ui {
                                mViewState.value = CharacterVS.AddCharacter(mCharacterVMMapper.map(it))
                            }
                        }
                }
            } catch (e: Exception) {
                ui {
                    mViewState.value = CharacterVS.Error(e.message)
                }
            }
            mViewState.value = CharacterVS.ShowLoader(false)
        }
    }
}