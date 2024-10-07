package com.mozhimen.pokemongo.now.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozhimen.pokemongo.now.db.mos.EntityPokemonInfo

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Dao
interface DaoPokemonInfo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityPokemonInfo: EntityPokemonInfo)

    @Query("SELECT * FROM EntityPokemonInfo where name = :name")
    suspend fun get_ofName(name: String): EntityPokemonInfo?

    @Query("DELETE FROM EntityPokemonInfo where id = :id")
    suspend fun delete(id: Int)
}