package dev.rockstar.portfolio.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rockstar.portfolio.core.database.entity.AssetEntity

@Database(
    entities = [AssetEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PortfolioDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao

}