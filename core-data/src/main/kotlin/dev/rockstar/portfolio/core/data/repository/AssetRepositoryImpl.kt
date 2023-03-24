package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.VisibleForTesting
import dev.rockstar.portfolio.core.database.AssetDao
import dev.rockstar.portfolio.core.database.entity.mapper.asDomain
import dev.rockstar.portfolio.core.model.Asset
import dev.rockstar.portfolio.core.network.AppDispatcher
import dev.rockstar.portfolio.core.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@VisibleForTesting
class AssetRepositoryImpl @Inject constructor(
    private val assetDao: AssetDao,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : AssetRepository {

    override fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Asset>> = flow {
        val assets = assetDao.getAssetList()
        if (assets.isEmpty()) {
            onError("There are no assets in the database")
        } else {
            emit(assets.asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}