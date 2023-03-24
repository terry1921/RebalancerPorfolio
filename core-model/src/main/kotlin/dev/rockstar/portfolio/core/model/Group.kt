package dev.rockstar.portfolio.core.model

data class Group(
    val id: Long,
    var name: String = "",
    var targetAllocation: Float,
    var note: String = "",
)