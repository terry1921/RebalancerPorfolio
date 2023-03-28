package dev.rockstar.portfolio.core.database.entity.mapper

import dev.rockstar.portfolio.core.database.entity.GroupEntity
import dev.rockstar.portfolio.core.model.Group

object GroupEntityMapper : EntityMapper<Group, GroupEntity> {

    override fun asEntity(domain: Group): GroupEntity {
        return GroupEntity(
            id = domain.id,
            name = domain.name,
            targetAllocation = domain.targetAllocation,
            note = domain.note
        )
    }

    override fun asDomain(entity: GroupEntity): Group {
        return Group(
            id = entity.id,
            name = entity.name,
            targetAllocation = entity.targetAllocation,
            note = entity.note
        )
    }

}

fun Group.asEntity(): GroupEntity {
    return GroupEntityMapper.asEntity(this)
}

fun List<Group>.asEntity(): List<GroupEntity> {
    return map { it.asEntity() }
}

fun GroupEntity.asDomain(): Group {
    return GroupEntityMapper.asDomain(this)
}

fun List<GroupEntity>.asDomain(): List<Group> {
    return map { it.asDomain() }
}