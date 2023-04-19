package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.VisibleForTesting
import dev.rockstar.portfolio.core.database.AssetDao
import dev.rockstar.portfolio.core.database.entity.mapper.asDomain
import dev.rockstar.portfolio.core.database.entity.mapper.asEntity
import dev.rockstar.portfolio.core.model.Asset
import dev.rockstar.portfolio.core.network.AppDispatcher
import dev.rockstar.portfolio.core.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject


/** The AssetRepositoryImpl class is a Kotlin implementation of the AssetRepository interface that
 * provides methods for fetching, inserting, getting, and updating assets using a database and
 * coroutines.
 */
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

    override fun insertAsset(
        asset: Asset,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean> = flow {
        val rowId = assetDao.insertAsset(asset.asEntity())
        Timber.d("rowId: $rowId")
        emit(rowId > 0)
    }.onStart { onStart() }.onCompletion { onComplete() }.catch { onError(it.message) }
        .flowOn(ioDispatcher)

    override fun getAsset(
        id: Long,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Asset> = flow {
        val asset = assetDao.getAssetById(id)
        if (asset == null) {
            onError("There is no asset with id: $id")
        } else {
            emit(asset.asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override fun updateAsset(
        asset: Asset,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean?> = flow {
        val rowId = assetDao.updateAsset(asset.asEntity())
        Timber.d("rowId: $rowId")
        emit(rowId > 0)
    }.onStart { onStart() }.onCompletion { onComplete() }.catch { onError(it.message) }
        .flowOn(ioDispatcher)
}