package com.hi.dhl.pokemon.dbs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hi.dhl.pokemon.dbs.helpers.LocalTypeConverter
import com.hi.dhl.pokemon.dbs.daos.PokemonDao
import com.hi.dhl.pokemon.dbs.daos.PokemonInfoDao
import com.hi.dhl.pokemon.dbs.daos.RemoteKeysDao
import com.hi.dhl.pokemon.dbs.mos.PokemonEntity
import com.hi.dhl.pokemon.dbs.mos.PokemonInfoEntity
import com.hi.dhl.pokemon.dbs.mos.RemoteKeysEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Database(
    entities = [PokemonEntity::class, RemoteKeysEntity::class, PokemonInfoEntity::class],
    version = 2, exportSchema = false
)
@TypeConverters(value = [LocalTypeConverter::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun pokemonInfoDao(): PokemonInfoDao

    companion object {
        @JvmStatic
        fun getAppDataBase(appContext: Context):AppDataBase = Room
            .databaseBuilder(appContext, AppDataBase::class.java, "dhl.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}
