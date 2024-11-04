package com.uvg.lab12anggelie.ktor

import kotlinx.serialization.Serializable
import com.uvg.lab12anggelie.Character

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)

@Serializable
data class CharacterListDto(
    val results: List<CharacterDto>
)

fun CharacterDto.mapToCharacterModel(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}
