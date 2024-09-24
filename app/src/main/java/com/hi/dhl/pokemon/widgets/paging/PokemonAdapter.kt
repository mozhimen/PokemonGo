package com.hi.dhl.pokemon.widgets.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.jdatabinding.dowithTry
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.RecycleItemPokemonBinding
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonItemModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

class PokemonAdapter : PagingDataAdapter<PokemonItemModel, PokemonAdapter.PokemonViewHolder>(DiffUtil_ItemCallback_PokemonItemModel()) {

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        dowithTry {
            val data = getItem(position)
            data?.let {
                holder.bindData(data, position)
            }
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

    class PokemonViewHolder(view: View) : DataBindingViewHolder<PokemonItemModel>(view) {
        private val mBinding: RecycleItemPokemonBinding by viewHolderBinding(view)

        override fun bindData(data: PokemonItemModel, position: Int) {
            mBinding.apply {
                data.id = "#${position + 1}"
                pokemon = data
                executePendingBindings()
            }
        }
    }
}

