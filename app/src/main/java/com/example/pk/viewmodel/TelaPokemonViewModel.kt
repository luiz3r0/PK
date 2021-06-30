package com.example.pk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pk.model.PokemonsItemModel
import com.example.pk.model.PokemonsModel
import com.example.pk.retrofit.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TelaPokemonViewModel: ViewModel()
{
    //private lateinit var retrofit: Retrofit
    private lateinit var retrofitService: RetrofitService
    private lateinit var callPokemonsModel: Call<PokemonsModel>
    private lateinit var pokemonsModel : PokemonsModel
    val listaPokemons: MutableLiveData<List<PokemonsItemModel>> by lazy {
        MutableLiveData<List<PokemonsItemModel>>().also {
            iniciarCarregarListaPokemons()
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getListaPokemons() : LiveData<List<PokemonsItemModel>>
    {
        return listaPokemons
    }

    fun iniciarCarregarListaPokemons()
    {
        viewModelScope.launch {
            retrofitService = retrofit.create(RetrofitService::class.java)
            callPokemonsModel = retrofitService.listaPokemons(150, 0)
            callPokemonsModel.enqueue(object : Callback<PokemonsModel>
            {
                override fun onResponse(call: Call<PokemonsModel>, response: Response<PokemonsModel>)
                {
                    if(response.isSuccessful)
                    {
                        pokemonsModel = response.body()!!
                        listaPokemons.postValue(pokemonsModel.results)
                    }
                }

                override fun onFailure(call: Call<PokemonsModel>, t: Throwable)
                {
                    call.cancel()
                }
            })
            }

    }
}