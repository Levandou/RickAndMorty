package com.velagissellint.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.velagissellint.data.api.ApiService
import com.velagissellint.domain.models.Result
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val apiService: ApiService
) : RxPagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition)
        return if (page == null)
            null
        else
            page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Result>> {
        val position = params.key ?: 1
        val pageSize = params.loadSize

        return apiService.getCharacters(position.toString())
            .subscribeOn(Schedulers.io())
            .firstOrError()
            .map { root -> root.results }
            .map {
                LoadResult.Page(
                    data = it,
                    nextKey = if (pageSize > it.size) null else position + 1,
                    prevKey = if (position == 1) null else position - 1
                ) as LoadResult<Int, Result>
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }
}
