package com.mozhimen.pokemongo.now.dis

import com.mozhimen.netk.retrofit2.NetKRetrofit
import com.mozhimen.pokemongo.now.restful.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
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
object ModuleRemote {

//    /**
//     * @Provides 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
//     * @Singleton 提供单例
//     */
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl("https://pokeapi.co/api/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun providerNetKRetrofit(): NetKRetrofit =
        NetKRetrofit("https://pokeapi.co/api/v2/", _converterFactory = GsonConverterFactory.create())

    @Provides
    @Singleton
    fun providePokemonService(netKRetrofit: NetKRetrofit): PokemonService =
         netKRetrofit.create(PokemonService::class.java)
}
