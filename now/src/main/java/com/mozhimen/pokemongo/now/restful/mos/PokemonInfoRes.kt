package com.mozhimen.pokemongo.now.restful.mos

import com.google.gson.annotations.SerializedName
import com.mozhimen.kotlin.utilk.kotlin.getEmptyOrDefault
import com.mozhimen.pokemongo.now.db.mos.EntityPokemonInfo

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
data class PokemonInfoRes(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val experience: Int,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("sprites") val sprites: Sprites
) {

    data class Sprites(
        @SerializedName("back_default") val backDefault: String,
        @SerializedName("back_female") val backFemale: String,
        @SerializedName("back_shiny") val backShiny: String,
        @SerializedName("back_shiny_female") val backShinyFemale: String,
        @SerializedName("front_default") val frontDefault: String,
        @SerializedName("front_female") val frontfemale: String,
        @SerializedName("front_shiny") val frontShiny: String,
        @SerializedName("front_shiny_female") val frontShinyFemale: String
    )

    data class Types(val slot: Int, val type: Type) {
        data class Type(val name: String, val url: String)
    }

    data class Stats(@SerializedName("base_stat") val baseStat: Int, val stat: Stat) {
        data class Stat(val name: String, val url: String)
    }

    /////////////////////////////////////////////////////////////

    fun convert2PokemonInfoEntity(): EntityPokemonInfo {
        return this.run {

            val dbTypes = mutableListOf<EntityPokemonInfo.Type>()
            val dbStats = mutableListOf<EntityPokemonInfo.Stats>()

            types.forEach {
                dbTypes.add(
                    EntityPokemonInfo.Type(
                        name = it.type.name,
                        url = it.type.url
                    )
                )
            }

            stats.forEach {
                dbStats.add(
                    EntityPokemonInfo.Stats(
                        baseStat = it.baseStat,
                        name = it.stat.name,
                        url = it.stat.url
                    )
                )
            }

            val dbSprites = EntityPokemonInfo.Sprites(
                backDefault = sprites.backDefault.getEmptyOrDefault(),
                backFemale = sprites.backFemale.getEmptyOrDefault(),
                backShiny = sprites.backShiny.getEmptyOrDefault(),
                backShinyFemale = sprites.backShinyFemale.getEmptyOrDefault(),
                frontDefault = sprites.frontDefault.getEmptyOrDefault(),
                frontfemale = sprites.frontfemale.getEmptyOrDefault(),
                frontShiny = sprites.frontShiny.getEmptyOrDefault(),
                frontShinyFemale = sprites.frontShinyFemale.getEmptyOrDefault()
            )

            EntityPokemonInfo(
                name = name,
                height = height,
                weight = weight,
                experience = experience,
                types = dbTypes,
                stats = dbStats,
                sprites = dbSprites
            )
        }
    }

    /////////////////////////////////////////////////////////////

    override fun toString(): String {
        return "NetWorkPokemonInfo(name='$name', height=$height, weight=$weight, experience=$experience, types=$types, stats=$stats, sprites=$sprites)"
    }
}