package com.velagissellint.domain.models

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: Location,
    val image: String,
    val episode: List<String>
)
