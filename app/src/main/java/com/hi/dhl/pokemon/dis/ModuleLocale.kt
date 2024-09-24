package com.hi.dhl.pokemon.dis

import android.app.Application
import android.content.Context
import com.hi.dhl.pokemon.dbs.AppDataBase
import com.hi.dhl.pokemon.dbs.daos.PokemonDao
import com.hi.dhl.pokemon.dbs.daos.PokemonInfoDao
import com.hi.dhl.pokemon.dbs.daos.RemoteKeysDao
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
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.getAppDataBase(context)

    @Provides
    @Singleton
    fun providerPokemonDao(appDataBase: AppDataBase): PokemonDao =
        appDataBase.pokemonDao()

    @Provides
    @Singleton
    fun providerPokemonInfoDao(appDataBase: AppDataBase): PokemonInfoDao =
        appDataBase.pokemonInfoDao()

    @Provides
    @Singleton
    fun providerRemoteKeysDao(appDataBase: AppDataBase): RemoteKeysDao =
        appDataBase.remoteKeysDao()
}