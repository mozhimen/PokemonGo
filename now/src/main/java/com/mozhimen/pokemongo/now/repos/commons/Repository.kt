package com.mozhimen.pokemongo.now.repos.commons

import androidx.paging.PagingData
import com.mozhimen.pokemongo.now.restful.cons.PokemonResult
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonInfoModel
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonItemModel
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