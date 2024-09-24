package com.hi.dhl.pokemon.dbs.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.pokemon.dbs.mos.PokemonEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity where name LIKE '%' || :parameter || '%'")
    fun get_ofPagingSource_nameLike(parameter: String): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM PokemonEntity")
    fun get_ofPagingSource(): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonList: List<PokemonEntity>)

    @Query("DELETE FROM PokemonEntity where remoteName = :name")
    suspend fun delete(name: String)
}