package com.hi.dhl.pokemon.repos.impls

import com.hi.dhl.pokemon.repos.commons.Mapper
import com.hi.dhl.pokemon.dbs.mos.PokemonEntity
import com.hi.dhl.pokemon.widgets.paging.mos.PokemonItemModel

/**
 * <pre>M
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class Entity2ItemModelMapper : Mapper<PokemonEntity, PokemonItemModel> {

    override fun map(input: PokemonEntity): PokemonItemModel =
        PokemonItemModel(name = input.name, url = input.url)

}