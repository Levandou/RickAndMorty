package com.velagissellint.rickAndMorty.di.detailedCharacter

import androidx.lifecycle.ViewModel
import com.velagissellint.presentation.detailed_character.DetailedCharacterViewModel
import com.velagissellint.rickAndMorty.ViewModelKey
import com.velagissellint.rickAndMorty.scopes.DetailedCharacterScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailedCharacterModule {
    @DetailedCharacterScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailedCharacterViewModel::class)
    fun bindViewModelFactory(detailedCharacterViewModel: DetailedCharacterViewModel): ViewModel
}
