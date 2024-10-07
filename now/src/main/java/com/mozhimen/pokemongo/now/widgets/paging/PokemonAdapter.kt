package com.mozhimen.pokemongo.now.widgets.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import com.mozhimen.bindk.utils.viewDataBinding
import com.mozhimen.pokemongo.now.databinding.RecycleItemPokemonBinding
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonItemModel
import com.mozhimen.pokemongo.now.R
import com.mozhimen.xmlk.vhk.VHKRecycler

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

class PokemonAdapter : PagingDataAdapter<PokemonItemModel, PokemonAdapter.PokemonViewHolder>(DiffUtil_ItemCallback_PokemonItemModel()) {

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
            val data = getItem(position)
            data?.let {
                holder.bindData(data, position)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = inflateView(parent, R.layout.recycle_item_pokemon)
        return PokemonViewHolder(view)
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }

    ///////////////////////////////////////////////////////////////////

    class PokemonViewHolder(view: View) : VHKRecycler(view) {
        private val mBinding: RecycleItemPokemonBinding by viewDataBinding(view)

        fun bindData(data: PokemonItemModel, position: Int) {
            mBinding.apply {
                data.id = "#${position + 1}"
                pokemon = data
                executePendingBindings()
            }
        }
    }
}

