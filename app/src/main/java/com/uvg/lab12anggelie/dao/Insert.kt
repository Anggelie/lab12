package com.uvg.lab12anggelie.dao

import com.uvg.lab12anggelie.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Insert
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
}

annotation class Insert

annotation class Query(val value: String)

annotation class Dao
