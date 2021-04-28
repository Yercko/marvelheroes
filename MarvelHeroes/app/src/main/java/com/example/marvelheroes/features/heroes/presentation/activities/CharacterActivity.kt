package com.example.marvelheroes.features.heroes.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.marvelheroes.R
import com.example.marvelheroes.features.heroes.presentation.viewmodel.CharacterViewModel
import com.example.marvelheroes.features.heroes.presentation.viewmodel.state.CharacterVS
import kotlinx.android.synthetic.main.activity_character.*
import kotlinx.android.synthetic.main.character_item.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModel()
    private  var id: Int = 0

    companion object {
        private const val ARG_ID = "arg_id"
        fun createInstance(context: Context, id: Int): Intent {
            return Intent(context, CharacterActivity::class.java).putExtras(
                Bundle().apply {
                    putInt(ARG_ID, id)
                }
            )
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        observeViewModel()
        intent.extras.let {
            id = it?.getInt(ARG_ID) ?: 0
            viewModel.getCharacterByID(id.toString())
        }

    }

    private fun observeViewModel() {
        viewModel.viewState.observe(this@CharacterActivity, {
            when (it) {
                is CharacterVS.AddCharacter -> {
                    tvTitle.text = it.characterVM.items?.get(0)?.name
                    tvDescription.text = it.characterVM?.items?.get(0)?.description
                    val url = it.characterVM?.items?.get(0)?.thumbnail?.path +"." + it.characterVM?.items?.get(0)?.thumbnail?.extension
                    Glide.with(this).load(url).into(ivIcon);

                }
                is CharacterVS.ShowLoader ->{
                    if(it.showLoader){
                        pbPost.visibility = View.VISIBLE
                        ll_content.visibility = View.GONE
                    }else{
                        pbPost.visibility = View.GONE
                        ll_content.visibility = View.VISIBLE
                    }
                }
                is CharacterVS.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
