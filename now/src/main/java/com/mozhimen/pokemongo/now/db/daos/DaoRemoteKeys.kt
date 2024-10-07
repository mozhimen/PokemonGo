package com.mozhimen.pokemongo.now.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozhimen.pokemongo.now.db.mos.EntityRemoteKeys

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Dao
interface DaoRemoteKeys {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeyEntity: EntityRemoteKeys)

    @Query("SELECT * FROM EntityRemoteKeys where remoteName = :name ")
    suspend fun getRemoteKeys(name: String): EntityRemoteKeys?

    @Query("DELETE FROM EntityRemoteKeys where remoteName = :name")
    suspend fun clearRemoteKeys(name: String)
}