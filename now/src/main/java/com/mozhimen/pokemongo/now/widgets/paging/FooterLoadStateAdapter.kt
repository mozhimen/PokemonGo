package com.mozhimen.pokemongo.now.widgets.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mozhimen.bindk.utils.viewDataBinding
import com.mozhimen.pokemongo.now.R
import com.mozhimen.kotlin.utilk.android.view.applyVisibleIfElseGone
import com.mozhimen.pokemongo.now.databinding.RecycieItemNetworkStateBinding
import com.mozhimen.xmlk.vhk.VHKRecycler

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class FooterLoadStateAdapter(val adapter: PokemonAdapter) : LoadStateAdapter<FooterLoadStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        val params = holder.itemView.layoutParams
        if (params is StaggeredGridLayoutManager.LayoutParams) {
            params.isFullSpan = true
        }
        holder.bindData(loadState, 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = inflateView(parent, R.layout.recycie_item_network_state)
        return LoadStateViewHolder(view) { adapter.retry() }
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }

    class LoadStateViewHolder(view: View, private val retryCallback: () -> Unit) : VHKRecycler(view) {
        val mBinding: RecycieItemNetworkStateBinding by viewDataBinding(view)

        fun bindData(data: LoadState, position: Int) {
            mBinding.apply {
                // 正在加载，显示进度条
                progress.applyVisibleIfElseGone(data is LoadState.Loading)
                // 加载失败，显示并点击重试按钮
                retryButton.applyVisibleIfElseGone(data is LoadState.Error)
                retryButton.setOnClickListener { retryCallback() }
                // 加载失败显示错误原因
                errorMsg.applyVisibleIfElseGone(!(data as? LoadState.Error)?.error?.message.isNullOrBlank())
                errorMsg.text = (data as? LoadState.Error)?.error?.message

                executePendingBindings()
            }
        }
    }
}