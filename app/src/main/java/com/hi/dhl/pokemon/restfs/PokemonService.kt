package com.hi.dhl.pokemon.restfs

import com.hi.dhl.pokemon.restfs.mos.ListingRes
import com.hi.dhl.pokemon.restfs.mos.PokemonInfoRes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface PokemonService {
    @GET("pokemon")
    suspend fun fetchPokemonList(@Query("limit") limit: Int = 20, @Query("offset") offset: Int = 0): ListingRes

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): PokemonInfoRes
}