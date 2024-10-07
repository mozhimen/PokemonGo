package com.mozhimen.pokemongo.now.dis

import android.content.Context
import com.mozhimen.pokemongo.now.db.DataBasePokemon
import com.mozhimen.pokemongo.now.db.daos.DaoPokemon
import com.mozhimen.pokemongo.now.db.daos.DaoPokemonInfo
import com.mozhimen.pokemongo.now.db.daos.DaoRemoteKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
// 这里使用了 ApplicationComponent，因此 NetworkModule 绑定到 Application 的生命周期。
object ModuleLocale {

    /**
     * @Provides 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
     * @Singleton 提供单例
     */
    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): DataBasePokemon =
        DataBasePokemon.getAppDataBase(context)

    @Provides
    @Singleton
    fun providerPokemonDao(dataBasePokemon: DataBasePokemon): DaoPokemon =
        dataBasePokemon.pokemonDao()

    @Provides
    @Singleton
    fun providerPokemonInfoDao(dataBasePokemon: DataBasePokemon): DaoPokemonInfo =
        dataBasePokemon.pokemonInfoDao()

    @Provides
    @Singleton
    fun providerRemoteKeysDao(dataBasePokemon: DataBasePokemon): DaoRemoteKeys =
        dataBasePokemon.remoteKeysDao()
}