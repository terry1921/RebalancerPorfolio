package dev.rockstar.portfolio.core.database

import androidx.room.*
import dev.rockstar.portfolio.core.database.entity.GroupEntity

/**
 * This is a Kotlin DAO interface that defines functions for inserting, retrieving, updating, and
 * deleting group entities from a database table.
 */
@Dao
interface GroupDao {

    /**
     * This Kotlin function inserts a GroupEntity object into a database table, replacing any existing
     * data with the same primary key.
     *
     * @param group The parameter "group" is an object of type GroupEntity that is being passed as an
     * argument to the function. It represents a single row of data that needs to be inserted into a
     * database table. The function is using Room's @Insert annotation with the onConflict parameter
     * set to REPLACE, which means
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupEntity): Long

    /**
     * This function retrieves a list of all group entities from a database using a SQL query.
     */
    @Query("SELECT * FROM GroupEntity")
    suspend fun getGroupList(): List<GroupEntity>

    /**
     * This Kotlin function queries a database to retrieve a GroupEntity object by its ID.
     *
     * @param groupId The parameter `groupId` is a `Long` value that represents the unique identifier
     * of a group entity in a database table. This function is a suspend function, which means it can
     * be called from a coroutine and will not block the main thread. The function uses a SQL query to
     * retrieve a group entity
     */
    @Query("SELECT * FROM GroupEntity WHERE id = :groupId")
    suspend fun getGroupById(groupId: Long): GroupEntity?

    /**
     * This is a suspend function that updates a GroupEntity and returns an integer value.
     *
     * @param group The parameter "group" is of type "GroupEntity", which is likely a data class or
     * entity class representing a group in some kind of system or application. This function is likely
     * used to update an existing group with new information or changes. The function is marked with
     * "suspend" which suggests that it
     */
    @Update
    suspend fun updateGroup(group: GroupEntity): Int

    /**
     * This function deletes a group entity from the database based on its ID.
     *
     * @param groupId The parameter `groupId` is a `Long` value that represents the unique identifier
     * of a group entity that needs to be deleted from the database. The `@Query` annotation is used to
     * define a custom SQL query that will be executed to delete the group entity with the specified
     * `groupId` from the
     */
    @Query("DELETE FROM GroupEntity WHERE id = :groupId")
    suspend fun deleteGroupById(groupId: Long): Int

    /**
     * This function deletes all groups from a database table called GroupEntity.
     */
    @Query("DELETE FROM GroupEntity")
    suspend fun deleteAllGroups()

}