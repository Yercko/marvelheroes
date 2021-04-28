package com.example.marvelheroes.features.heroes.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelheroes.R
import com.example.marvelheroes.features.heroes.presentation.adapters.RVCharactersAdapter
import com.example.marvelheroes.features.heroes.presentation.viewmodel.CharactersViewModel
import com.example.marvelheroes.features.heroes.presentation.viewmodel.state.CharacterVS
import com.example.marvelheroes.features.heroes.presentation.viewmodel.state.CharactersVS
import kotlinx.android.synthetic.main.activity_characters.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity  : AppCompatActivity(), RVCharactersAdapter.Listener {

    private val viewModel: CharactersViewModel by viewModel()
    private val mAdapter = RVCharactersAdapter()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        observeViewModel()
        mAdapter.setListener(this)

        rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@CharactersActivity)
            adapter = mAdapter
        }

        viewModel.getCharacters()
    }

    private fun observeViewModel(){
        viewModel.viewState.observe(this@CharactersActivity, {
            when (it) {
                is CharactersVS.AddCharacter -> {
                    mAdapter.add(it.characterVM)
                }
                is CharactersVS.ShowLoader ->{
                    if(it.showLoader){
                        pbCharacter.visibility = View.VISIBLE
                        rvCharacters.visibility = View.GONE
                    }else{
                        pbCharacter.visibility = View.GONE
                        rvCharacters.visibility = View.VISIBLE
                    }
                }
                is CharactersVS.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onCharacterClicked(id: Int) {
        startActivity(CharacterActivity.createInstance(context = this, id = id))
    }
}
