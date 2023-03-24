package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.WorkerThread
import dev.rockstar.portfolio.core.model.Asset
import kotlinx.coroutines.flow.Flow

interface AssetRepository {

    @WorkerThread
    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Asset>>

}