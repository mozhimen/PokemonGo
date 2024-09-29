package com.mozhimen.pokemongo.now.widgets.paging

import android.view.View
import com.hi.dhl.jdatabinding.DataBindingListAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.mozhimen.pokemongo.now.databinding.RecycleItemAlbumBinding
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonInfoModel
import com.mozhimen.pokemongo.now.R

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  :
 * </pre>
 */

class AlbumAdapter : DataBindingListAdapter<PokemonInfoModel.AlbumModel>(DiffUtil_ItemCallback_AlbumModel()) {
    override fun layout(position: Int): Int =
        R.layout.recycle_item_album

    override fun viewHolder(layout: Int, view: View): DataBindingViewHolder<PokemonInfoModel.AlbumModel> =
        AlbumViewHolder(view)

    ///////////////////////////////////////////////////////////////////

    class AlbumViewHolder(view: View) : DataBindingViewHolder<PokemonInfoModel.AlbumModel>(view) {

        private val mBinding: RecycleItemAlbumBinding by viewHolderBinding(view)

        override fun bindData(data: PokemonInfoModel.AlbumModel, position: Int) {
            mBinding.apply {
                album = data
                executePendingBindings()
            }
        }
    }
}