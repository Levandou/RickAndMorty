package com.velagissellint.domain.useCases.character

import com.velagissellint.domain.models.Character
import io.reactivex.rxjava3.core.Observable

interface DetailedCharacterRepository {
    fun getCharacter(id: String): Observable<Character>
}
