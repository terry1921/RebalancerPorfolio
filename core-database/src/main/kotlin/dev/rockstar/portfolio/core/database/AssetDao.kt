package dev.rockstar.portfolio.core.database

import androidx.room.*
import dev.rockstar.portfolio.core.database.entity.AssetEntity

@Dao
interface AssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(asset: AssetEntity): Long

    @Query("SELECT * FROM AssetEntity ORDER BY group_id ASC")
    suspend fun getAssetList(): List<AssetEntity>

    @Query("SELECT * FROM AssetEntity WHERE id = :assetId")
    suspend fun getAssetById(assetId: Long): AssetEntity?

    @Update
    suspend fun updateAsset(asset: AssetEntity): Int

    @Query("DELETE FROM AssetEntity WHERE id = :assetId")
    suspend fun deleteAssetById(assetId: Long): Int

    @Query("DELETE FROM AssetEntity")
    suspend fun deleteAllAssets()


}