package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.WorkerThread
import dev.rockstar.portfolio.core.model.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    @WorkerThread
    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Group>>

    @WorkerThread
    fun insertGroup(
        group: Group,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean>

}
