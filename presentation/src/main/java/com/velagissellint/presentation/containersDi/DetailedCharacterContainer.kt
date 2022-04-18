package com.velagissellint.presentation.containersDi

import com.velagissellint.presentation.detailed_character.DetailedCharacterFragment

interface DetailedCharacterContainer {
    fun inject(detailedCharacterFragment: DetailedCharacterFragment)
}
