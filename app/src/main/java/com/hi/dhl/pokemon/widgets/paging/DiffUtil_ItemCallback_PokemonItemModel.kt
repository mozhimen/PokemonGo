package com.hi.dhl.pokemon.widgets.paging

import androidx.recyclerview.widget.DiffUtil
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonItemModel

/**
 * @ClassName DiffUtil_ItemCallback_PokemonItemModel
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/23 22:07
 * @Version 1.0
 */
class DiffUtil_ItemCallback_PokemonItemModel : DiffUtil.ItemCallback<PokemonItemModel>() {
    override fun areItemsTheSame(
        oldItem: PokemonItemModel,
        newItem: PokemonItemModel
    ): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: PokemonItemModel,
        newItem: PokemonItemModel
    ): Boolean =
        oldItem == newItem
}
