package com.velagissellint.presentation.containersDi

import com.velagissellint.presentation.list_of_characters.ListOfCharactersFragment

interface ListOfCharactersContainer {
    fun inject(listOfCharactersFragment: ListOfCharactersFragment)
}
