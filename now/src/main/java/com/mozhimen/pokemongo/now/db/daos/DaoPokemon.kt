package com.mozhimen.pokemongo.now.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozhimen.pokemongo.now.db.mos.EntityPokemon

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Dao
interface DaoPokemon {
    @Query("SELECT * FROM EntityPokemon where name LIKE '%' || :parameter || '%'")
    fun get_ofPagingSource_nameLike(parameter: String): PagingSource<Int, EntityPokemon>

    @Query("SELECT * FROM EntityPokemon")
    fun get_ofPagingSource(): PagingSource<Int, EntityPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonList: List<EntityPokemon>)

    @Query("DELETE FROM EntityPokemon where remoteName = :name")
    suspend fun delete(name: String)
}