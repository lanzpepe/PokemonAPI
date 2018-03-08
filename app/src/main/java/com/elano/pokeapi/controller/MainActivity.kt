package com.elano.pokeapi.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.elano.pokeapi.R
import com.elano.pokeapi.adapter.PokemonAdapter
import com.elano.pokeapi.model.Pokemon
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = "https://pokeapi.co/api/v2/pokemon/"
    private var mPokemons: ArrayList<Pokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPokemons = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    private fun fetchJson() {
        doAsync {
            progressBar.visibility = View.VISIBLE
            for (i in 1..20) {
                val resultJson = URL(url + i).readText(); val jsonObject = JSONObject(resultJson)
                val name = StringUtils.capitalize(jsonObject.getString("name"))
                val imageUrl = jsonObject.getJSONObject("sprites").getString("front_default")
                uiThread {
                    recyclerView.adapter = PokemonAdapter(mPokemons)
                    mPokemons?.add(Pokemon(name, imageUrl))
                    tvHeading.text = "Great! You will now have $i Pokemon(s)~"
                }
            }
            progressBar.visibility = View.GONE
        }
    }
}