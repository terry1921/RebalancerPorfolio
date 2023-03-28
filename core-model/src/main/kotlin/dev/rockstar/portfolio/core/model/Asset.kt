package dev.rockstar.portfolio.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.math.roundToInt

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
        return "${targetAllocation.getTargetNumber()}%"
    }

    fun getMountCurrency(): String {
        val formatter: java.text.NumberFormat = java.text.DecimalFormat("#,###.##")
        return "$${formatter.format(amount)}"
    }

    fun getTitleHeader() : String {
        return "$name (${getTarget()})"
    }

}

fun Float.getTargetNumber() : String {
    val target = if (this % 1 == 0.0f) {
        this.roundToInt().toString()
    } else {
        this.toString()
    }
    return target
}