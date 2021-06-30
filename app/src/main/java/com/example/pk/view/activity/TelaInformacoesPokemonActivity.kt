package com.example.pk.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.pk.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TelaInformacoesPokemonActivity : AppCompatActivity()
{
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var pokemonImagemUrl: String
    private lateinit var pokemonNome: String
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_informacoes_pokemon)
        iniciarToolBar()
        iniciarBotaoVoltar()
        iniciarFonteTitulo()
        iniciarInformacoesPokemon()
        iniciarFloatingActionButtonCompartilharPokemon()
    }

    override fun onBackPressed()
    {
        iniciarFecharTela()
    }

    override fun onOptionsItemSelected(mMenuItem: MenuItem): Boolean
    {
        if (mMenuItem.itemId == android.R.id.home)
        {
            finish()
            return true
        }
        return super.onOptionsItemSelected(mMenuItem)
    }

    private fun iniciarToolBar()
    {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun iniciarBotaoVoltar()
    {
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun iniciarFonteTitulo()
    {
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout_tela_informacoes_pokemon)
        collapsingToolbarLayout.setCollapsedTitleTypeface(ResourcesCompat.getFont(this,
            R.font.android
        ))
        collapsingToolbarLayout.setExpandedTitleTypeface(ResourcesCompat.getFont(this,
            R.font.android
        ))
    }

    private fun iniciarInformacoesPokemon()
    {
        textView = findViewById(R.id.textview_activity_tela_informacoes_pokemon_content_scrolling)
        imageView = findViewById(R.id.imageview_activity_tela_informacoes_produto)
        pokemonImagemUrl = intent.getStringExtra("Po_ImagemUrl").toString()
        pokemonNome = intent.getStringExtra("Po_Nome").toString()
        Glide.with(this)
            .load(pokemonImagemUrl)
            .fitCenter()
            .thumbnail(0.1f)
            .into(imageView)

        textView.text = pokemonNome
    }

    private fun iniciarFloatingActionButtonCompartilharPokemon()
    {
        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, pokemonImagemUrl)
                type = "text/html"
            }
            startActivity(sendIntent)
        }
    }

    private fun iniciarFecharTela()
    {
        finish()
    }
}