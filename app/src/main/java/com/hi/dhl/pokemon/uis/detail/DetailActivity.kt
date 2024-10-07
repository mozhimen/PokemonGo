package com.hi.dhl.pokemon.uis.detail

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.cons.CParams
import com.hi.dhl.pokemon.databinding.ActivityDetailsBinding
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonItemModel
import com.mozhimen.bindk.utils.viewBinding
import com.mozhimen.kotlin.utilk.android.content.startContext
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val mBindingActivity: ActivityDetailsBinding by viewBinding()
    private val mViewModel: DetailViewModel by viewModels()
    lateinit var mPokemonModel: PokemonItemModel

    /**
     *
     * 由于这里演示如何通过 Fragment 的构造函数传递参数
     * 之前在 DetailActivity 演示 Activity 和 ViewModel 结合 Flow 的三种使用方式，代码已经移动到了 [DetailFragment]，使用方法都是一样的
     * 对应分析文章地址： https://juejin.im/post/5f153adff265da22fb287e6e
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         * 这里演示的是 Fragment 1.2.0 上重要的更新： 通过 Fragment 的构造函数传递参数，以及 FragmentFactory 和 FragmentContainerView 的使用
         *
         * 详情查看 Fragment 1.2.0  分析的文章 https://juejin.im/post/5ecb16f1f265da76fb0c3967
         *
         * 由于 hilt 不支持 FragmentFactory 注入，所这里是通过手动注入的 [CustomFragmentFactory]
         */
        supportFragmentManager.fragmentFactory = CustomFragmentFactory()
        super.onCreate(savedInstanceState)

        mBindingActivity.apply {
            mPokemonModel = requireNotNull(intent.getParcelableExtra(CParams.KEY_LIST_MODEL)) { "params is not null" }

            DetailFragment.addFragment(
                supportFragmentManager,
                mPokemonModel,
                R.id.fragmentContainer
            )
        }
    }

    class CustomFragmentFactory : FragmentFactory() {
        @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
            when (className) {
                DetailFragment::class.java.name -> DetailFragment(DetailFragment::class.java.simpleName)
                else -> super.instantiate(classLoader, className)
            }
    }

    companion object {
        @JvmStatic
        fun startActivity_ofDetail(act: Context, params: PokemonItemModel) {
            act.startContext<DetailActivity> {
                putExtra(CParams.KEY_LIST_MODEL, params)
            }
        }
    }
}