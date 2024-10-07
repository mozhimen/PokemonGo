package com.mozhimen.pokemongo.now.dis

import androidx.paging.PagingConfig
import com.mozhimen.pokemongo.now.db.DataBasePokemon
import com.mozhimen.pokemongo.now.repos.commons.Repository
import com.mozhimen.pokemongo.now.repos.impls.Entity2ItemModelMapper
import com.mozhimen.pokemongo.now.repos.impls.InfoEntity2InfoModelMapper
import com.mozhimen.pokemongo.now.repos.impls.PokemonRepositoryImpl
import com.mozhimen.pokemongo.now.restful.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleRepository {

    @Singleton
    @Provides
    fun providePagingConfig(): PagingConfig =
        PagingConfig(
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

    @Singleton
    @Provides
    fun provideTasksRepository(api: PokemonService, db: DataBasePokemon, pagingConfig: PagingConfig): Repository =
        PokemonRepositoryImpl(
            api,
            db,
            pagingConfig,
            Entity2ItemModelMapper(),
            InfoEntity2InfoModelMapper()
        )
}