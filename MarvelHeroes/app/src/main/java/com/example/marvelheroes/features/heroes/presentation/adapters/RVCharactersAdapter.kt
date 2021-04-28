package com.example.marvelheroes.features.heroes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.R
import com.example.marvelheroes.features.heroes.data.datasource.rest.response.ResultResponse
import com.example.marvelheroes.features.heroes.presentation.model.CharacterVM
import kotlinx.android.synthetic.main.character_item.view.*

class RVCharactersAdapter : RecyclerView.Adapter<RVCharactersAdapter.ViewHolder>() {

    private val mList: MutableList<ResultResponse> = mutableListOf()
    private var mListener: Listener? = null

    fun add(listCharacterVM: CharacterVM) {
        listCharacterVM.items?.forEach {
            mList.add(it)
        }
        notifyDataSetChanged()
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun onCharacterClicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(CharacterVM: ResultResponse) {
            Glide.with(itemView).load(CharacterVM.thumbnail.path +"." + CharacterVM.thumbnail.extension).into(itemView.iv_icon);
            itemView.tvCharacterItemTitle.text = CharacterVM.name
            itemView.tvSubtitle.text = CharacterVM.description
            itemView.cvCharacterItem.setOnClickListener {
                mListener?.onCharacterClicked(CharacterVM.id ?: 0)
            }
        }
    }
}