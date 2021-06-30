package com.example.pk.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pk.model.PokemonsItemModel
import com.example.pk.R
import com.example.pk.view.adapter.TelaPokemonAdapter
import com.example.pk.viewmodel.TelaPokemonViewModel
import androidx.activity.viewModels

class TelaPokemonActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var telaPokemonAdapter: TelaPokemonAdapter
    private var telaPokemonActivity: TelaPokemonActivity = this
    private var listaPokemons: List<PokemonsItemModel> = ArrayList()
    private val telaPokemonViewModel: TelaPokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_pokemon)
        iniciarToolBar()
        iniciarRecyclerView()
    }

    override fun onBackPressed()
    {
        iniciarFecharTela()
    }

    private fun iniciarToolBar()
    {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun iniciarRecyclerView()
    {
        recyclerView = findViewById(R.id.recyclerview_activity_tela_pokemon_content)
        recyclerView.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        telaPokemonAdapter = TelaPokemonAdapter(listaPokemons, telaPokemonActivity)
        recyclerView.adapter = telaPokemonAdapter
        telaPokemonViewModel.getListaPokemons().observe(this, { listaPokemons ->
            (recyclerView.adapter as TelaPokemonAdapter).adicionarListaPokemon(listaPokemons)
        })
    }

    private fun iniciarFecharTela()
    {
        finish()
    }
}