package com.mozhimen.pokemongo.now.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozhimen.pokemongo.now.db.mos.RemoteKeysEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeyEntity: RemoteKeysEntity)

    @Query("SELECT * FROM RemoteKeysEntity where remoteName = :name ")
    suspend fun getRemoteKeys(name: String): RemoteKeysEntity?

    @Query("DELETE FROM RemoteKeysEntity where remoteName = :name")
    suspend fun clearRemoteKeys(name: String)
}