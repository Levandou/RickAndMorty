package com.velagissellint.domain.useCases.character

import javax.inject.Inject

class GetDetailedCharacterUseCase @Inject constructor(
    private val detailedCharacterRepository: DetailedCharacterRepository
) {
    fun getCharacter(id: String) = detailedCharacterRepository.getCharacter(id)
}
