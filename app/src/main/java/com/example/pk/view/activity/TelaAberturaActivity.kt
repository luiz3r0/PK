package com.example.pk.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TelaAberturaActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        iniciarTelaPokemon()
    }

    override fun onBackPressed()
    {

    }

    private fun iniciarTelaPokemon()
    {
        val intent = Intent(this, TelaPokemonActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        iniciarFecharTela()
    }

    private fun iniciarFecharTela()
    {
        finish()
    }
}
