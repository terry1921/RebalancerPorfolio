package dev.rockstar.portfolio.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AssetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "group_id") val groupId: Long,
    var name: String = "",
    var amount: Float,
    @ColumnInfo(name = "target_allocation") var targetAllocation: Float,
    var note: String = ""
)
