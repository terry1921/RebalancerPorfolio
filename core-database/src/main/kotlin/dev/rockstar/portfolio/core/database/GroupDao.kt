package dev.rockstar.portfolio.core.database

import androidx.room.*
import dev.rockstar.portfolio.core.database.entity.GroupEntity

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupEntity)

    @Query("SELECT * FROM GroupEntity")
    suspend fun getGroupList(): List<GroupEntity>

    @Query("SELECT * FROM GroupEntity WHERE id = :groupId")
    suspend fun getGroupById(groupId: Long): GroupEntity?

    @Update
    suspend fun updateGroup(group: GroupEntity): Int

    @Query("DELETE FROM GroupEntity WHERE id = :groupId")
    suspend fun deleteGroupById(groupId: Long): Int

    @Query("DELETE FROM GroupEntity")
    suspend fun deleteAllGroups()

}