package com.velagissellint.rickAndMorty.di

import com.velagissellint.data.RepositoryImpl
import com.velagissellint.data.api.ApiService
import com.velagissellint.data.paging.PagingSource
import com.velagissellint.domain.useCases.character.DetailedCharacterRepository
import com.velagissellint.domain.useCases.paging.PagingSourceRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {
    @Provides
    fun providePagingSourceRepository(
        pagingSource: PagingSource,
        apiService: ApiService
    ): PagingSourceRepository = RepositoryImpl(pagingSource, apiService)

    @Provides
    fun provideDetailedCharacterRepository(
        pagingSource: PagingSource,
        apiService: ApiService
    ): DetailedCharacterRepository = RepositoryImpl(pagingSource, apiService)
}
