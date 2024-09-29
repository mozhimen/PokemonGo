package com.mozhimen.pokemongo.now.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozhimen.pokemongo.now.db.mos.PokemonInfoEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Dao
interface PokemonInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonInfoEntity: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity where name = :name")
    suspend fun get_ofName(name: String): PokemonInfoEntity?

    @Query("DELETE FROM PokemonInfoEntity where id = :id")
    suspend fun delete(id: Int)
}