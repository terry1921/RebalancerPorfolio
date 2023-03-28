package dev.rockstar.portfolio.core.database

import dev.rockstar.portfolio.core.database.entity.mapper.asEntity
import dev.rockstar.portfolio.core.test.MockUtil.mockAsset
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class AssetDaoTest : LocalDatabase() {

    private lateinit var assetDao: AssetDao

    @Before
    fun init() {
        assetDao = db.assetDao()
    }

    @Test
    fun insertAndLoadAssetTest() = runBlocking {
        assetDao.insertAsset(mockAsset().asEntity())

        val loadFromDB = assetDao.getAssetById(1)
        assertThat(loadFromDB.toString(), `is`(mockAsset().asEntity().toString()))
    }

    @Test
    fun insertAndLoadAssetListTest() = runBlocking {
        assetDao.insertAsset(mockAsset().asEntity())

        val loadFromDB = assetDao.getAssetList()

        val mockData = mockAsset().asEntity()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    @Test
    fun insertAndUpdateAssetTest() = runBlocking {
        assetDao.insertAsset(mockAsset().asEntity())

        val loadFromDB = assetDao.getAssetById(1)
        assertThat(loadFromDB.toString(), `is`(mockAsset().asEntity().toString()))

        val updatedAsset = mockAsset().copy(name = "updated")
        assetDao.updateAsset(updatedAsset.asEntity())

        val loadUpdatedFromDB = assetDao.getAssetById(1)
        assertThat(loadUpdatedFromDB.toString(), `is`(updatedAsset.asEntity().toString()))
    }

    @Test
    fun insertAndDeleteAssetByIdTest() = runBlocking {
        assetDao.insertAsset(mockAsset().asEntity())

        val loadFromDB = assetDao.getAssetById(1)
        assertThat(loadFromDB.toString(), `is`(mockAsset().asEntity().toString()))

        assetDao.deleteAssetById(1)

        val loadDeletedFromDB = assetDao.getAssetById(1)
        assertTrue("Asset is not deleted", loadDeletedFromDB == null)
    }

    @Test
    fun insertAndDeleteAllAssetsTest() = runBlocking {
        assetDao.insertAsset(mockAsset().asEntity())

        val loadFromDB = assetDao.getAssetById(1)
        assertThat(loadFromDB.toString(), `is`(mockAsset().asEntity().toString()))

        assetDao.deleteAllAssets()

        val loadDeletedFromDB = assetDao.getAssetById(1)
        assertTrue("Assets is not deleted", loadDeletedFromDB == null)
    }

}