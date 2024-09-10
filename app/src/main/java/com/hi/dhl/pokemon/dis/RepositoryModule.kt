package com.hi.dhl.pokemon.dis

import com.hi.dhl.pokemon.repos.PokemonFactory
import com.hi.dhl.pokemon.dbs.AppDataBase
import com.hi.dhl.pokemon.restfs.PokemonService
import com.hi.dhl.pokemon.repos.commons.Repository
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
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(api: PokemonService, db: AppDataBase): Repository =
        PokemonFactory.makePokemonRepository(api, db)
}