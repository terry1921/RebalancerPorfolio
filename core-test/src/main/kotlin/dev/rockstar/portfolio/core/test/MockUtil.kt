package dev.rockstar.portfolio.core.test

import dev.rockstar.portfolio.core.model.Asset

object MockUtil {

    fun myMockList() = listOf(mockAsset())

    fun mockAsset() = Asset(
        id = 1,
        name = "asset",
        groupId = 1,
        amount = 1.0f,
        targetAllocation = 1.0f,
        note = "note"
    )

}