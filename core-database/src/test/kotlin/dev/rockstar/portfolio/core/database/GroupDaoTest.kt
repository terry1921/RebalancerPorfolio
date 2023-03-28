package dev.rockstar.portfolio.core.database

import dev.rockstar.portfolio.core.database.entity.mapper.asEntity
import dev.rockstar.portfolio.core.test.MockUtil.mockGroup
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(org.robolectric.RobolectricTestRunner::class)
@Config(sdk = [23])
class GroupDaoTest : LocalDatabase() {

    private lateinit var groupDao: GroupDao

    @Before
    fun init() {
        groupDao = db.groupDao()
    }

    @Test
    fun insertAndLoadGroupTest() = runBlocking {
        groupDao.insertGroup(mockGroup().asEntity())

        val loadFromDB = groupDao.getGroupById(1)
        assertThat(loadFromDB.toString(), `is`(mockGroup().asEntity().toString()))
    }

    @Test
    fun insertAndLoadGroupListTest() = runBlocking {
        groupDao.insertGroup(mockGroup().asEntity())

        val loadFromDB = groupDao.getGroupList()

        val mockData = mockGroup().asEntity()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    @Test
    fun insertAndUpdateGroupTest() = runBlocking {
        groupDao.insertGroup(mockGroup().asEntity())

        val loadFromDB = groupDao.getGroupById(1)
        assertThat(loadFromDB.toString(), `is`(mockGroup().asEntity().toString()))

        val updatedGroup = mockGroup().copy(name = "updated")
        groupDao.updateGroup(updatedGroup.asEntity())

        val loadUpdatedFromDB = groupDao.getGroupById(1)
        assertThat(loadUpdatedFromDB.toString(), `is`(updatedGroup.asEntity().toString()))
    }

    @Test
    fun insertAndDeleteGroupByIdTest() = runBlocking {
        groupDao.insertGroup(mockGroup().asEntity())

        val loadFromDB = groupDao.getGroupById(1)
        assertThat(loadFromDB.toString(), `is`(mockGroup().asEntity().toString()))

        groupDao.deleteGroupById(1)

        val loadFromDBAfterDelete = groupDao.getGroupById(1)
        assertTrue("Group is not deleted", loadFromDBAfterDelete == null)
    }

    @Test
    fun insertAndDeleteAllGroupTest() = runBlocking {
        groupDao.insertGroup(mockGroup().asEntity())

        val loadFromDB = groupDao.getGroupList()
        assertThat(loadFromDB[0].toString(), `is`(mockGroup().asEntity().toString()))

        groupDao.deleteAllGroups()

        val loadFromDBAfterDelete = groupDao.getGroupById(1)
        assertTrue("Group is not deleted", loadFromDBAfterDelete == null)
    }

}