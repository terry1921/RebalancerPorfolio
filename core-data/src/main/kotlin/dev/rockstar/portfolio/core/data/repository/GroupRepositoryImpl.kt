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

/**
 * The GroupRepositoryImpl class is a Kotlin implementation of the GroupRepository interface that
 * provides methods for fetching, inserting, getting, and updating groups using a database and a
 * coroutine dispatcher.
 * */
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

    override fun getGroup(
        id: Long,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Group> = flow {
        val group = groupDao.getGroupById(id)
        if (group == null) {
            onError("There is no group with id: $id")
        } else {
            emit(group.asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.catch { onError(it.message) }
        .flowOn(ioDispatcher)

    override fun updateGroup(
        group: Group,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean?> = flow {
        val rowId = groupDao.updateGroup(group.asEntity())
        Timber.d("rowId: $rowId")
        emit(rowId > 0)
    }.onStart { onStart() }.onCompletion { onComplete() }.catch { onError(it.message) }
        .flowOn(ioDispatcher)

}
