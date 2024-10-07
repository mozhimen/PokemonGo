package com.mozhimen.pokemongo.now.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mozhimen.pokemongo.now.db.daos.DaoPokemon
import com.mozhimen.pokemongo.now.db.daos.DaoPokemonInfo
import com.mozhimen.pokemongo.now.db.daos.DaoRemoteKeys
import com.mozhimen.pokemongo.now.db.helpers.TypeConverterLocal
import com.mozhimen.pokemongo.now.db.mos.EntityPokemon
import com.mozhimen.pokemongo.now.db.mos.EntityPokemonInfo
import com.mozhimen.pokemongo.now.db.mos.EntityRemoteKeys

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Database(
    entities = [EntityPokemon::class, EntityRemoteKeys::class, EntityPokemonInfo::class],
    version = 2, exportSchema = false
)
@TypeConverters(value = [TypeConverterLocal::class])
abstract class DataBasePokemon : RoomDatabase() {
    abstract fun pokemonDao(): DaoPokemon
    abstract fun remoteKeysDao(): DaoRemoteKeys
    abstract fun pokemonInfoDao(): DaoPokemonInfo

    companion object {
        @JvmStatic
        fun getAppDataBase(appContext: Context):DataBasePokemon = Room
            .databaseBuilder(appContext, DataBasePokemon::class.java, "dhl.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}
