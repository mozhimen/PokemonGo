package com.hi.dhl.pokemon.helpers

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.hi.dhl.jprogressview.JProgressView
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonInfoModel
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonItemModel
import com.hi.dhl.pokemon.widgets.paging.AlbumAdapter
import com.hi.dhl.pokemon.uis.detail.DetailActivity
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@BindingAdapter("bindingAvator")
fun bindingAvator(imageView: ImageView, url: String) {
    UtilKLogWrapper.v("TAG", "tag: $url")
    imageView.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher_round)
    }
}

@BindingAdapter("bindSmallImage")
fun bindingSmallImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher_round)
        size(280, 280)
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isEnabled = false
}

@BindingAdapter("bindProgressValue", "bindProgressMaxValue")
fun bindingProgressView(progress: JProgressView, progressValue: Int, maxProgressValue: Int) {
    progress
        .setProgress(progressValue.toFloat())
        .setMaxProgress(maxProgressValue)
        .startAnimal()
}

@BindingAdapter("bindFinish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
    }
}

@BindingAdapter("bindClick")
fun bindingClick(view: View, model: PokemonItemModel) {
    view.setOnClickListener {
        DetailActivity.startActivity_ofDetail(view.context, model)
    }
}

@BindingAdapter("bindAdapter", "bindData")
fun bindingAdapter(
    recyclerView: RecyclerView,
    albumAdapter: AlbumAdapter,
    data: List<PokemonInfoModel.AlbumModel>?
) {
    data?.let {
        recyclerView.adapter = albumAdapter
        albumAdapter.submitList(data)
        albumAdapter.notifyDataSetChanged()
    }
}