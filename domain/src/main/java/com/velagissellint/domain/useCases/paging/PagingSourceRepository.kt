package com.velagissellint.domain.useCases.paging

import androidx.paging.PagingData
import com.velagissellint.domain.models.Result
import io.reactivex.rxjava3.core.Flowable

interface PagingSourceRepository {
    fun getCharacters(): Flowable<PagingData<Result>>
}
