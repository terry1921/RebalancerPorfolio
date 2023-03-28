package dev.rockstar.portfolio.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String = "",
    @ColumnInfo(name = "target_allocation") var targetAllocation: Float,
    var note: String = ""
)
