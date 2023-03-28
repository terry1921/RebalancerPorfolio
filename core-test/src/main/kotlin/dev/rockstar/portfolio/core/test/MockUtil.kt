package dev.rockstar.portfolio.core.test

import dev.rockstar.portfolio.core.model.Asset
import dev.rockstar.portfolio.core.model.Group

object MockUtil {

    fun mockList() = listOf(mockAsset())

    fun mockAsset() = Asset(
        id = 1,
        name = "asset",
        groupId = 1,
        amount = 1.0f,
        targetAllocation = 1.0f,
        note = "note"
    )

    fun mockListGroup() = listOf(mockGroup())

    fun mockGroup() = Group(
        id = 1,
        name = "group",
        targetAllocation = 1.0f,
        note = "note"
    )

}