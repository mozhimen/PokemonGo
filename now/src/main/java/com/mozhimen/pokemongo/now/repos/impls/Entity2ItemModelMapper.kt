package com.mozhimen.pokemongo.now.repos.impls

import coil.map.Mapper
import com.mozhimen.pokemongo.now.db.mos.EntityPokemon
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonItemModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class Entity2ItemModelMapper : Mapper<EntityPokemon, PokemonItemModel> {

    override fun map(input: EntityPokemon): PokemonItemModel =
        PokemonItemModel(name = input.name, url = input.url)

}