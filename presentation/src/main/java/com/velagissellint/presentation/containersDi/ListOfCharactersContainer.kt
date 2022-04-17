package com.velagissellint.presentation.containersDi

import com.velagissellint.presentation.ui.ListOfCharactersFragment

interface ListOfCharactersContainer {
    fun inject(listOfCharactersFragment: ListOfCharactersFragment)
}
