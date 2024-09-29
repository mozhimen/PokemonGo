package com.mozhimen.pokemongo.now.widgets.paging

import androidx.recyclerview.widget.DiffUtil
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonInfoModel

/**
 * @ClassName DiffUtil_ItemCallback_AlbumModel
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/23 22:00
 * @Version 1.0
 */
class DiffUtil_ItemCallback_AlbumModel : DiffUtil.ItemCallback<PokemonInfoModel.AlbumModel>() {
    override fun areItemsTheSame(
        oldItem: PokemonInfoModel.AlbumModel,
        newItem: PokemonInfoModel.AlbumModel
    ): Boolean =
        oldItem.index == newItem.index

    override fun areContentsTheSame(
        oldItem: PokemonInfoModel.AlbumModel,
        newItem: PokemonInfoModel.AlbumModel
    ): Boolean =
        oldItem == newItem
}