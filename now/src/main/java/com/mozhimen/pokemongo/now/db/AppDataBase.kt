package com.mozhimen.pokemongo.now.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mozhimen.pokemongo.now.db.daos.PokemonDao
import com.mozhimen.pokemongo.now.db.daos.PokemonInfoDao
import com.mozhimen.pokemongo.now.db.daos.RemoteKeysDao
import com.mozhimen.pokemongo.now.db.helpers.LocalTypeConverter
import com.mozhimen.pokemongo.now.db.mos.PokemonEntity
import com.mozhimen.pokemongo.now.db.mos.PokemonInfoEntity
import com.mozhimen.pokemongo.now.db.mos.RemoteKeysEntity

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
