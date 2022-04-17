package com.velagissellint.rickAndMorty.di.listOfCharacters

import androidx.lifecycle.ViewModel
import com.velagissellint.presentation.ui.ListOfCharactersViewModel
import com.velagissellint.rickAndMorty.ViewModelKey
import com.velagissellint.rickAndMorty.scopes.ListOfCharactersScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListOfCharactersModule {
    @ListOfCharactersScope
    @Binds
    @IntoMap
    @ViewModelKey(ListOfCharactersViewModel::class)
    fun bindViewModelFactory(listOfCharactersViewModel: ListOfCharactersViewModel): ViewModel
}
