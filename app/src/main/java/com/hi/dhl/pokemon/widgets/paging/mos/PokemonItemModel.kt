package com.hi.dhl.pokemon.widgets.paging.mos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Parcelize
data class PokemonItemModel(
    var id: String = "",
    val name: String,
    val url: String
) : Parcelable