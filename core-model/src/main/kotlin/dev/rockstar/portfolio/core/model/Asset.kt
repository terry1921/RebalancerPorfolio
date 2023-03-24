package dev.rockstar.portfolio.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Asset(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "groupId") var groupId: Long,
    @field:Json(name = "name") var name: String = "",
    @field:Json(name = "amount") var amount: Float,
    @field:Json(name = "targetAllocation") var targetAllocation: Float,
    @field:Json(name = "note") var note: String = "",
)