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
data class EntityPokemon(
    @PrimaryKey
    val name: String,
    var pokemonId: Int = 0,
    val page: Int = 0,
    val url: String,
    val remoteName: String
)