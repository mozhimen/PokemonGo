package com.mozhimen.pokemongo.now.repos.impls

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import coil.map.Mapper
import com.mozhimen.pokemongo.now.db.DataBasePokemon
import com.mozhimen.pokemongo.now.db.mos.EntityPokemon
import com.mozhimen.pokemongo.now.db.mos.EntityPokemonInfo
import com.mozhimen.pokemongo.now.repos.commons.Repository
import com.mozhimen.pokemongo.now.repos.helpers.PokemonRemoteMediator
import com.mozhimen.pokemongo.now.restful.PokemonService
import com.mozhimen.pokemongo.now.restful.cons.PokemonResult
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonInfoModel
import com.mozhimen.pokemongo.now.widgets.paging.mos.PokemonItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

class PokemonRepositoryImpl(
    val api: PokemonService,
    val db: DataBasePokemon,
    val pageConfig: PagingConfig,
    val mapper2ItemMolde: Mapper<EntityPokemon, PokemonItemModel>,
    val mapper2InfoModel: Mapper<EntityPokemonInfo, PokemonInfoModel>
) : Repository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchPokemonList(): Flow<PagingData<PokemonItemModel>> {
        return Pager(
            config = pageConfig,
            remoteMediator = PokemonRemoteMediator(api, db)
        ) {
            db.pokemonDao().get_ofPagingSource()
        }.flow.map { pagingData ->
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    override suspend fun fetchPokemonByParameter(parameter: String): Flow<PagingData<PokemonItemModel>> {
        return Pager(pageConfig) {
            // 加载数据库的数据
            db.pokemonDao().get_ofPagingSource_nameLike(parameter)
        }.flow.map { pagingData ->

            // 数据映射，数据库实体 PersonEntity ——>  上层用到的实体 Person
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    override suspend fun fetchPokemonInfo(name: String): Flow<PokemonResult<PokemonInfoModel>> {
        return flow {
            try {
                val pokemonDao = db.pokemonInfoDao()
                // 查询数据库是否存在，如果不存在请求网络
                var infoModel = pokemonDao.get_ofName(name)
                if (infoModel == null) {
                    // 网络请求
                    val netWorkPokemonInfo = api.fetchPokemonInfo(name)

                    // 将网路请求的数据，换转成的数据库的 model，之后插入数据库
                    infoModel = netWorkPokemonInfo.convert2PokemonInfoEntity()
                    // 插入更新数据库
                    pokemonDao.insert(infoModel)
                }
                // 将数据源的 model 转换成上层用到的 model，
                // ui 不能直接持有数据源，防止数据源的变化，影响上层的 ui
                val model = mapper2InfoModel.map(infoModel)

                // 发射转换后的数据
                emit(PokemonResult.Success(model))
            } catch (e: Exception) {
                emit(PokemonResult.Failure(e.cause))
            }
        }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程
    }



    companion object {
        private val TAG = "PokemonRepositoryImpl"
    }
}