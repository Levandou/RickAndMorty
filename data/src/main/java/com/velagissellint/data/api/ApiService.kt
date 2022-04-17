package com.velagissellint.data.api

import com.velagissellint.domain.pojo.Root
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    fun getCharacters(
        @Query(QUERY_PAGE) page: String
    ): Observable<Root>

    companion object {
        private const val QUERY_PAGE = "page"
    }
}
