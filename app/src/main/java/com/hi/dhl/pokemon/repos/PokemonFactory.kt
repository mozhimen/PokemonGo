package com.hi.dhl.pokemon.repos

import androidx.paging.PagingConfig
import com.hi.dhl.pokemon.dbs.AppDataBase
import com.hi.dhl.pokemon.repos.impls.Entity2ItemModelMapper
import com.hi.dhl.pokemon.repos.impls.InfoEntity2InfoModelMapper
import com.hi.dhl.pokemon.restfs.PokemonService
import com.hi.dhl.pokemon.repos.impls.PokemonRepositoryImpl
import com.hi.dhl.pokemon.repos.commons.Repository

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
object PokemonFactory {

    fun makePokemonRepository(api: PokemonService, db: AppDataBase): Repository =
        PokemonRepositoryImpl(
            api,
            db,
            pagingConfig,
            Entity2ItemModelMapper(),
            InfoEntity2InfoModelMapper()
        )

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 30,

        // 开启占位符
        enablePlaceholders = true,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 4,

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */
        initialLoadSize = 30
    )
}