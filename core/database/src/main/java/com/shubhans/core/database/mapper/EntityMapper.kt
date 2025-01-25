package com.shubhans.core.database.mapper

interface EntityMapper<Domain, Entity> {
    fun asDomain(entity: Entity): Domain

    fun asEntity(domain: Domain): Entity
}