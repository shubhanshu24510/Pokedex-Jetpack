package com.shubhans.core.database.mapper

import com.shubhans.core.database.entity.PokemonInfoEntity
import com.shubhans.model.PokemonInfo

object PokemonInfoEntityMapper : EntityMapper<PokemonInfo, PokemonInfoEntity> {

    override fun asEntity(domain: PokemonInfo): PokemonInfoEntity {
        return PokemonInfoEntity(
            id = domain.id,
            name = domain.name,
            height = domain.height,
            weight = domain.weight,
            types = domain.types,
            stats = domain.stats,
            exp = domain.exp,
            experience = domain.experience,
        )
    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {
        return PokemonInfo(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            types = entity.types,
            stats = entity.stats,
            exp = entity.exp,
            experience = entity.experience,
        )
    }
}

fun PokemonInfo.asEntity(): PokemonInfoEntity {
    return PokemonInfoEntityMapper.asEntity(this)
}

fun PokemonInfoEntity.asDomain(): PokemonInfo {
    return PokemonInfoEntityMapper.asDomain(this)
}