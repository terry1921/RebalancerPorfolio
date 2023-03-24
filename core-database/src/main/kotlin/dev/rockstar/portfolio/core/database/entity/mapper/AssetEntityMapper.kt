package dev.rockstar.portfolio.core.database.entity.mapper

import dev.rockstar.portfolio.core.database.entity.AssetEntity
import dev.rockstar.portfolio.core.model.Asset

object AssetEntityMapper : EntityMapper<Asset, AssetEntity> {

    override fun asEntity(domain: Asset): AssetEntity {
        return AssetEntity(
            id = domain.id,
            groupId = domain.groupId,
            name = domain.name,
            amount = domain.amount,
            targetAllocation = domain.targetAllocation,
            note = domain.note
        )
    }

    override fun asDomain(entity: AssetEntity): Asset {
        return Asset(
            id = entity.id,
            groupId = entity.groupId,
            name = entity.name,
            amount = entity.amount,
            targetAllocation = entity.targetAllocation,
            note = entity.note
        )
    }

}

fun Asset.asEntity(): AssetEntity {
    return AssetEntityMapper.asEntity(this)
}

fun List<Asset>.asEntity(): List<AssetEntity> {
    return map { it.asEntity() }
}

fun List<AssetEntity>.asDomain(): List<Asset> {
    return map { it.asDomain() }
}

fun AssetEntity.asDomain(): Asset {
    return AssetEntityMapper.asDomain(this)
}