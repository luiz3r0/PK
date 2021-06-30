package com.example.pk.retrofit

import com.example.pk.model.PokemonsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService
{
    @GET("pokemon")
    fun listaPokemons(@Query("limit") limit : Int, @Query("offset") offset: Int): Call<PokemonsModel>
}