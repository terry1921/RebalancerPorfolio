package dev.rockstar.portfolio.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rockstar.portfolio.core.database.entity.AssetEntity
import dev.rockstar.portfolio.core.database.entity.GroupEntity

@Database(
    entities = [AssetEntity::class, GroupEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PortfolioDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao
    abstract fun groupDao(): GroupDao

}