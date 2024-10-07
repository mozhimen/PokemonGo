package com.mozhimen.pokemongo.now.widgets.paging

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.mozhimen.bindk.utils.viewDataBinding
import com.mozhimen.pokemongo.now.databinding.RecycleItemAlbumBinding
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonInfoModel
import com.mozhimen.pokemongo.now.R
import com.mozhimen.xmlk.recyclerk.adapter.RecyclerKListAdapter
import com.mozhimen.xmlk.vhk.VHKRecycler

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  :
 * </pre>
 */

class AlbumAdapter : RecyclerKListAdapter<PokemonInfoModel.AlbumModel, AlbumAdapter.AlbumViewHolder>(DiffUtil_ItemCallback_AlbumModel()) {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(parent,R.layout.recycle_item_album)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, item: PokemonInfoModel.AlbumModel?, position: Int) {
        super.onBindViewHolder(holder, item, position)
        item?:return
        holder.bindData(item,position)
    }

    ///////////////////////////////////////////////////////////////////

    class AlbumViewHolder(parent: ViewGroup,@LayoutRes intResLayout:Int) : VHKRecycler(parent,intResLayout) {

        private val mBinding: RecycleItemAlbumBinding by viewDataBinding(itemView)

        fun bindData(data: PokemonInfoModel.AlbumModel, position: Int) {
            mBinding.apply {
                album = data
                executePendingBindings()
            }
        }
    }
}