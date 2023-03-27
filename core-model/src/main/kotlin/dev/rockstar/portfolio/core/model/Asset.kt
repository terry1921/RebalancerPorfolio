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
) {
    fun getTarget() : String {
        return "${getTargetNumber()}%"
    }

    private fun getTargetNumber() : String {
        val target = if (targetAllocation % 1 == 0.0f) {
            targetAllocation.toInt().toString()
        } else {
            targetAllocation.toString()
        }
        return target
    }

    fun getMountCurrency(): String {
        val formatter: java.text.NumberFormat = java.text.DecimalFormat("#,###.##")
        return "$${formatter.format(amount)}"
    }

    fun getTitleHeader() : String {
        return "$name (${getTarget()})"
    }

}