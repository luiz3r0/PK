package com.example.pk.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient
{
     private val baseUrl: String = "https://pokeapi.co/api/v2/"

     fun iniciarRetrofit() : Retrofit
     {
          return  Retrofit.Builder()
               .baseUrl(baseUrl)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
     }
}