package com.elano.pokeapi.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elano.pokeapi.R
import com.elano.pokeapi.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_row_main.view.*

/**
 * Created by Jess on 3/8/2018.
 */
class PokemonAdapter(val pokemons: ArrayList<Pokemon>?) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder =
            MyViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.content_row_main, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val pokemon = pokemons!![position]

        holder?.tvName?.text = pokemon.name
        Picasso.with(holder?.itemView?.context).load(pokemon.imageUrl).into(holder?.ivImage)
    }

    override fun getItemCount(): Int = pokemons!!.size

    inner class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val ivImage = view?.ivImageHolder
        val tvName = view?.tvNameHolder
    }
}