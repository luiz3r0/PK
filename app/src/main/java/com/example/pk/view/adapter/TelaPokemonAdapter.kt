package com.example.pk.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pk.*
import com.example.pk.model.PokemonsItemModel
import com.example.pk.view.activity.TelaInformacoesPokemonActivity
import com.example.pk.view.activity.TelaPokemonActivity

class TelaPokemonAdapter(private var listaPokemons: List<PokemonsItemModel>, private var telaPokemonActivity: TelaPokemonActivity) : RecyclerView.Adapter<TelaPokemonAdapter.ViewHolderTelaPokemon>()
{
    class ViewHolderTelaPokemon(view: View) : RecyclerView.ViewHolder(view)
    {
        val imagem: ImageView = view.findViewById(R.id.imageview_tela_pokemon)
        val nome: TextView = view.findViewById(R.id.textview_tela_pokemon_nome)
        val cardView: CardView = view.findViewById(R.id.cardview_tela_pokemon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderTelaPokemon
    {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_tela_pokemon_lista_pokemon, viewGroup, false)
        return ViewHolderTelaPokemon(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolderTelaPokemon, position: Int)
    {
           Glide.with(telaPokemonActivity)
               .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (position+1) + ".png")
               .fitCenter()
               .thumbnail(0.1f)
               .into(viewHolder.imagem)

            viewHolder.nome.text = listaPokemons[position].name

            viewHolder.cardView.setOnClickListener {
                val intent = Intent(telaPokemonActivity, TelaInformacoesPokemonActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                intent.putExtra(
                    "Po_ImagemUrl",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (position + 1) + ".png"
                )
                intent.putExtra("Po_Nome", listaPokemons[position].name)
                telaPokemonActivity.startActivity(intent)
            }
    }

    override fun getItemCount(): Int = listaPokemons.size

    fun adicionarListaPokemon(listaPokemons: List<PokemonsItemModel>)
    {
        this.listaPokemons = listaPokemons
        notifyDataSetChanged()
    }
}