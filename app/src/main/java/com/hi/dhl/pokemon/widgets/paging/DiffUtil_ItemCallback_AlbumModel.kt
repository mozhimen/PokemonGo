package com.hi.dhl.pokemon.widgets.paging

import androidx.recyclerview.widget.DiffUtil
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonInfoModel.AlbumModel

/**
 * @ClassName DiffUtil_ItemCallback_AlbumModel
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/23 22:00
 * @Version 1.0
 */
class DiffUtil_ItemCallback_AlbumModel : DiffUtil.ItemCallback<AlbumModel>() {
    override fun areItemsTheSame(
        oldItem: AlbumModel,
        newItem: AlbumModel
    ): Boolean =
        oldItem.index == newItem.index

    override fun areContentsTheSame(
        oldItem: AlbumModel,
        newItem: AlbumModel
    ): Boolean =
        oldItem == newItem
}