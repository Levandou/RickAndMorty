package com.velagissellint.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.velagissellint.data.paging.PagingSource
import com.velagissellint.domain.pojo.Result
import com.velagissellint.domain.useCases.paging.PagingSourceRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val pagingSource: PagingSource
) : PagingSourceRepository {
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
}
