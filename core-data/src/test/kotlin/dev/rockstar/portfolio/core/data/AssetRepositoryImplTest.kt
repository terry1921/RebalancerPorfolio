package dev.rockstar.portfolio.core.data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.rockstar.portfolio.core.data.repository.AssetRepositoryImpl
import dev.rockstar.portfolio.core.database.AssetDao
import dev.rockstar.portfolio.core.database.entity.mapper.asEntity
import dev.rockstar.portfolio.core.test.MainCoroutinesRule
import dev.rockstar.portfolio.core.test.MockUtil.mockAsset
import dev.rockstar.portfolio.core.test.MockUtil.mockList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class AssetRepositoryImplTest {

    private lateinit var repository: AssetRepositoryImpl
    private val assetDao: AssetDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        repository = AssetRepositoryImpl(assetDao, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchAssetListFromDatabaseTest() = runTest {
        whenever(assetDao.getAssetList()).thenReturn(mockList().asEntity())

        repository.fetchData(
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.id, 1)
            assertEquals(expectItem.name, "asset")
            assertEquals(expectItem, mockAsset())
            awaitComplete()
        }

        verify(assetDao, atLeastOnce()).getAssetList()
    }

}