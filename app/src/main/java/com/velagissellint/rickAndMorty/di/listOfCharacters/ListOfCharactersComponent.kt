package com.velagissellint.rickAndMorty.di.listOfCharacters

import com.velagissellint.presentation.containersDi.ListOfCharactersContainer
import com.velagissellint.rickAndMorty.di.RepositoriesModule
import com.velagissellint.rickAndMorty.di.RetrofitModule
import com.velagissellint.rickAndMorty.scopes.ListOfCharactersScope
import dagger.Subcomponent

@ListOfCharactersScope
@Subcomponent(
    modules = [ListOfCharactersModule::class,
        RetrofitModule::class,
        RepositoriesModule::class]
)
interface ListOfCharactersComponent : ListOfCharactersContainer
