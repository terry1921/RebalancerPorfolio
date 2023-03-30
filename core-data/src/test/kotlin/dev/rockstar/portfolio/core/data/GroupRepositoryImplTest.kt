package dev.rockstar.portfolio.core.data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.rockstar.portfolio.core.data.repository.GroupRepositoryImpl
import dev.rockstar.portfolio.core.database.GroupDao
import dev.rockstar.portfolio.core.database.entity.mapper.asEntity
import dev.rockstar.portfolio.core.test.MainCoroutinesRule
import dev.rockstar.portfolio.core.test.MockUtil.mockGroup
import dev.rockstar.portfolio.core.test.MockUtil.mockListGroup
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class GroupRepositoryImplTest {

    private lateinit var repository: GroupRepositoryImpl
    private val groupDao: GroupDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        repository = GroupRepositoryImpl(groupDao, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchGroupListFromDatabaseTest() = runTest {
        whenever(groupDao.getGroupList()).thenReturn(mockListGroup().asEntity())

        repository.fetchData(
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectedItem = awaitItem()[0]
            assertEquals(expectedItem.id, 1)
            assertEquals(expectedItem.name, "group")
            assertEquals(expectedItem, mockGroup())
            awaitComplete()
        }

        verify(groupDao, atLeastOnce()).getGroupList()
    }

    @Test
    fun insertGroupDatabaseTest() = runTest {
        whenever(groupDao.insertGroup(mockGroup().asEntity())).thenReturn(1)

        repository.insertGroup(
            group = mockGroup(),
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectedItem = awaitItem()
            assertEquals(expectedItem, true)
            awaitComplete()
        }

        verify(groupDao, atLeastOnce()).insertGroup(mockGroup().asEntity())
    }

}