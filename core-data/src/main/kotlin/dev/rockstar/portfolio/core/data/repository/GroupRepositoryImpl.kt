package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.VisibleForTesting
import dev.rockstar.portfolio.core.database.GroupDao
import dev.rockstar.portfolio.core.database.entity.mapper.asDomain
import dev.rockstar.portfolio.core.model.Group
import dev.rockstar.portfolio.core.network.AppDispatcher
import dev.rockstar.portfolio.core.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@VisibleForTesting
class GroupRepositoryImpl @Inject constructor (
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

}
