package com.velagissellint.domain.useCases.paging

import androidx.paging.PagingData
import com.velagissellint.domain.pojo.Result
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetCharactersPagesUseCase @Inject constructor(
    private val pagingSourceRepository: PagingSourceRepository
) {
    fun getCharacters(): Flowable<PagingData<Result>> = pagingSourceRepository.getCharacters()
}
