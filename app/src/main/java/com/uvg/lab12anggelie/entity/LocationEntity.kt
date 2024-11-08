package com.uvg.lab12anggelie.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.lab12anggelie.Location

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

fun LocationEntity.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}