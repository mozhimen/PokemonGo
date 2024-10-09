package com.mozhimen.pokemongo.now.repos.impls

import coil.map.Mapper
import com.mozhimen.pokemongo.now.db.mos.EntityPokemonInfo
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonInfoModel


/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class InfoEntity2InfoModelMapper : Mapper<EntityPokemonInfo, PokemonInfoModel> {

    override fun map(input: EntityPokemonInfo): PokemonInfoModel {

        return convert2PokemonInfoEntity(input)
    }

    fun convert2PokemonInfoEntity(pokemonInfoModel: EntityPokemonInfo): PokemonInfoModel {
        return pokemonInfoModel.run {

            val dbTypes = mutableListOf<PokemonInfoModel.Type>()
            val dbStats = mutableListOf<PokemonInfoModel.Stats>()

            types.forEach {
                dbTypes.add(
                    PokemonInfoModel.Type(
                        name = it.name,
                        url = it.url
                    )
                )
            }

            stats.forEach {
                dbStats.add(
                    PokemonInfoModel.Stats(
                        baseStat = it.baseStat,
                        name = it.name,
                        url = it.url
                    )
                )
            }

            val albumsItem = mutableListOf<PokemonInfoModel.AlbumModel>()
            if (!sprites.backDefault.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 1, url = sprites.backDefault))
            }
            if (!sprites.backShiny.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 3, url = sprites.backShiny))
            }
            if (!sprites.frontDefault.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 5, url = sprites.frontDefault))
            }
            if (!sprites.frontShiny.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 7, url = sprites.frontShiny))
            }

            PokemonInfoModel(
                name = name,
                height = height,
                weight = weight,
                experience = experience,
                types = dbTypes,
                stats = dbStats,
                albums = albumsItem
            )
        }
    }

}