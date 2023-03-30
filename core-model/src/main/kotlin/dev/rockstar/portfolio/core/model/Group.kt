package dev.rockstar.portfolio.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.math.roundToInt

@JsonClass(generateAdapter = true)
data class Group(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") var name: String = "",
    @field:Json(name = "targetAllocation") var targetAllocation: Float,
    @field:Json(name = "note") var note: String = "",
) {

    constructor(name: String, targetAllocation: Float, note: String) : this(
        0L,
        name,
        targetAllocation,
        note
    )

    fun getTarget(): String {
        return "${targetAllocation.getTargetNumber()}%"
    }

    fun getTargetInt(): Int {
        return targetAllocation.roundToInt()
    }

}