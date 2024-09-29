package com.mozhimen.pokemongo.now.db.helpers

import androidx.room.TypeConverter
import com.mozhimen.pokemongo.now.db.mos.PokemonInfoEntity
import com.mozhimen.serialk.gson.strJson2t_ofType_ofGson
import com.mozhimen.serialk.gson.t2strJson_ofGson

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/21
 *     desc  :
 * </pre>
 */
open class LocalTypeConverter {

    @TypeConverter
    fun json2StatsEntity(src: String): List<PokemonInfoEntity.Stats>? =
        src.strJson2t_ofType_ofGson()

    @TypeConverter
    fun statsEntity2Json(data: List<PokemonInfoEntity.Stats>): String =
        data.t2strJson_ofGson()

    @TypeConverter
    fun json2TypeEntity(src: String): List<PokemonInfoEntity.Type>? =
        src.strJson2t_ofType_ofGson()

    @TypeConverter
    fun typeEntity2Json(data: List<PokemonInfoEntity.Type>): String =
        data.t2strJson_ofGson()//GsonBuilder().create().typedToJson(data)

}