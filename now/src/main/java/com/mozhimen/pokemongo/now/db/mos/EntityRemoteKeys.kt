package com.mozhimen.pokemongo.now.db.mos

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Entity
data class EntityRemoteKeys constructor(
    @PrimaryKey
    val remoteName: String,
    val nextKey: Int?
)