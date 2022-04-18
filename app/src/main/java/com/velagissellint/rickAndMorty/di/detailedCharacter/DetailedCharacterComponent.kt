package com.velagissellint.rickAndMorty.di.detailedCharacter

import com.velagissellint.presentation.containersDi.DetailedCharacterContainer
import com.velagissellint.rickAndMorty.di.RepositoriesModule
import com.velagissellint.rickAndMorty.di.RetrofitModule
import com.velagissellint.rickAndMorty.scopes.DetailedCharacterScope
import dagger.Subcomponent

@DetailedCharacterScope
@Subcomponent(
    modules = [DetailedCharacterModule::class,
        RetrofitModule::class,
        RepositoriesModule::class]
)
interface DetailedCharacterComponent : DetailedCharacterContainer
