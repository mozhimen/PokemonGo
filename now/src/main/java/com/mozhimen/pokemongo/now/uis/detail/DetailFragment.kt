package com.mozhimen.pokemongo.now.uis.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.mozhimen.bindk.utils.viewBinding
import com.mozhimen.bindk.utils.viewDataBinding
import com.mozhimen.pokemongo.now.R
import com.mozhimen.pokemongo.now.databinding.FragmentDetailsBinding
import com.mozhimen.pokemongo.now.widgets.paging.AlbumAdapter
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  : 演示的是使用带参数的 Fragment
 * </pre>
 */

// 如果使用带参数的 Fragment 需要设置 FragmentFactory，告诉系统如何实例化 Fragment
@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment(args: String) : Fragment(R.layout.fragment_details) {

    private val mBinding: FragmentDetailsBinding by viewDataBinding()
    private val mViewModel: DetailViewModel by activityViewModels()
    private lateinit var mPokemonModel: PokemonItemModel
    val mAlbumAdapter: AlbumAdapter by lazy { AlbumAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPokemonModel = requireNotNull(arguments?.getParcelable(KEY_LIST_MODEL)) {
            "params is not null"
        }

        mBinding.apply {
            pokemonListModel = mPokemonModel
            albumAdapter = mAlbumAdapter
            viewModel = mViewModel.apply {
                fectchPokemonInfo2(mPokemonModel.name)
                    .observe(viewLifecycleOwner, Observer {})
            }
            lifecycleOwner = this@DetailFragment
        }

        mViewModel.failure.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        /**
         * 这三种方式的使用和 之前在 [DetailActivity] 使用方式一样
         */

//        // 方法一
//        mViewModel.pokemon.observe(this, Observer {
//            // 将数据显示在页面上
//        })
//
//        // 方法二
//        mViewModel.fectchPokemonInfo2(mPokemonModel.name).observe(this, Observer {
//            // 将数据显示在页面上
//        })
//
//        // 方法三
//        lifecycleScope.launch {
//            mViewModel.apply {
//                fectchPokemonInfo3(mPokemonModel.name).observe(viewLifecycleOwner, Observer {
//                    // 将数据显示在页面上
//                })
//            }
//        }
    }

    companion object {
        private val KEY_LIST_MODEL = "listModel"

        fun addFragment(
            manager: FragmentManager,
            params: PokemonItemModel,
            fragmentContainerId: Int
        ) {

            manager.commit {
                val bundle = bundleOf(KEY_LIST_MODEL to params)
                replace(fragmentContainerId, DetailFragment::class.java, bundle)
            }
        }
    }
}