package com.velagissellint.domain.pojo

data class Root(
    val results: List<Result>
)

data class Result(
    val id: Long,
    val name: String,
    val species: String,
    val gender: String,
    val image: String
)
