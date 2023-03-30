package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.VisibleForTesting
import dev.rockstar.portfolio.core.database.GroupDao
import dev.rockstar.portfolio.core.database.entity.mapper.asDomain
import dev.rockstar.portfolio.core.database.entity.mapper.asEntity
import dev.rockstar.portfolio.core.model.Group
import dev.rockstar.portfolio.core.network.AppDispatcher
import dev.rockstar.portfolio.core.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@VisibleForTesting
class GroupRepositoryImpl @Inject constructor(
    private val groupDao: GroupDao,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : GroupRepository {

    override fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Group>> = flow {
        val groups = groupDao.getGroupList()
        if (groups.isEmpty()) {
            onError("There are no groups in the database")
        } else {
            emit(groups.asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override fun insertGroup(
        group: Group,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean> = flow {
        val rowId = groupDao.insertGroup(group.asEntity())
        Timber.d("rowId: $rowId")
        emit(rowId > 0)
    }.onStart { onStart() }.onCompletion { onComplete() }.catch { onError(it.message) }
        .flowOn(ioDispatcher)


}
