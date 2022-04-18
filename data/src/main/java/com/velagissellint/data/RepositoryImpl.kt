package com.velagissellint.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.velagissellint.data.api.ApiService
import com.velagissellint.data.paging.PagingSource
import com.velagissellint.domain.models.Character
import com.velagissellint.domain.models.Result
import com.velagissellint.domain.useCases.character.DetailedCharacterRepository
import com.velagissellint.domain.useCases.paging.PagingSourceRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val pagingSource: PagingSource,
    private val apiService: ApiService
) : PagingSourceRepository, DetailedCharacterRepository {
    override fun getCharacters(): Flowable<PagingData<Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = false,
            initialLoadSize = 20,
            maxSize = 100
        ),
        pagingSourceFactory = { pagingSource }
    ).flowable

    override fun getCharacter(id: String): Observable<Character> =
        apiService.getCharacter(id)
}
