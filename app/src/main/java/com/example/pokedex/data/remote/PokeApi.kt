package com.example.pokedex.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.data.remote.response.PokemonList

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon

}
