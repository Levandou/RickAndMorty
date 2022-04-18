package com.velagissellint.presentation.containersDi

interface AppContainer {
    fun plusListOfCharactersComponent(): ListOfCharactersContainer
    fun plusDetailedCharacterComponent(): DetailedCharacterContainer
}
