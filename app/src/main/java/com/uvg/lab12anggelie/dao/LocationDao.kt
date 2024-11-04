package com.uvg.lab12anggelie.dao

import com.uvg.lab12anggelie.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Insert()
    suspend fun insertLocations(locations: List<LocationEntity>)

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocationById(id: Int): LocationEntity?

    @Query("DELETE FROM locations")
    suspend fun deleteAllLocations()
}