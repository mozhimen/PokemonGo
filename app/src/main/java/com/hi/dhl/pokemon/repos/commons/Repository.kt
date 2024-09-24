package com.hi.dhl.pokemon.repos.commons

import androidx.paging.PagingData
import com.hi.dhl.pokemon.restfs.cons.PokemonResult
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonInfoModel
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonItemModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface Repository {
    fun fetchPokemonList(): Flow<PagingData<PokemonItemModel>>

    suspend fun fetchPokemonInfo(name: String): Flow<PokemonResult<PokemonInfoModel>>

    suspend fun fetchPokemonByParameter(parameter: String): Flow<PagingData<PokemonItemModel>>
}