package com.mozhimen.pokemongo.now.uis.main

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mozhimen.mvvmk.bases.activity.databinding.BaseActivityVDBVM
import com.mozhimen.pokemongo.now.databinding.ActivityMainBinding
import com.mozhimen.pokemongo.now.widgets.paging.FooterLoadStateAdapter
import com.mozhimen.pokemongo.now.widgets.paging.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivityVDBVM<ActivityMainBinding, MainViewModel>() {
    private val mPokemonAdapter by lazy { PokemonAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        vdb.recyleView.adapter = mPokemonAdapter.withLoadStateFooter(FooterLoadStateAdapter(mPokemonAdapter))

        /**
         * 分为 数据库 和 网络搜索
         * 可以运行注释掉的代码，文章链接：https://juejin.cn/post/6854573220457086990
         */
        vdb.layoutHeader.searchView.addTextChangedListener {
            val result = it.toString()
            vm.queryParameterForDb(result) // 搜索数据库
//                mViewModel.queryParameterForNetWork(result) // 网络搜索
        }
    }

    override fun initObserver() {
        lifecycleScope.launchWhenCreated {
            mPokemonAdapter.loadStateFlow.collectLatest { state ->
                vdb.swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }

        vm.postOfData().observe(this, Observer {
            mPokemonAdapter.submitData(lifecycle, it)
            vdb.swiperRefresh.isEnabled = false
        })

        // 数据库搜索回调监听
        vm.searchResultForDb.observe(this, Observer {
            mPokemonAdapter.submitData(lifecycle, it)
        })

        // 网络搜索回调监听
        vm.searchResultMockNetWork.observe(this, Observer {
//            mPokemonAdapter.submitData(lifecycle, it)
        })
    }

    override fun bindViewVM(vdb: ActivityMainBinding) {
        vdb.mainViewModel = vm
    }

    /**
     *  callbackFlow 提供了一个简单的回调 Api，并且在关闭的时候，移除注册监听
     *  在很多场景都可以使用，例如定位 locatoinManager#requestSingleUpdate 在 awaitClose 移除掉监听
     */
//    fun AppCompatEditText.addTextChangedListenerFlow(): Flow<String> = callbackFlow {
//        val watch = addTextChangedListener {
//            sendBlocking(it.toString().trim())
//        }
//        addTextChangedListener(watch)
////        awaitClose { removeTextChangedListener(watch) }
//    }
}