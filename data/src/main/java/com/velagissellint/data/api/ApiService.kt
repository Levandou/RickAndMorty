package com.velagissellint.data.api

import com.velagissellint.domain.models.Character
import com.velagissellint.domain.models.Root
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    fun getCharacters(
        @Query(QUERY_PAGE) page: String
    ): Observable<Root>

    @GET("character/{$PATCH_ID}")
    fun getCharacter(
        @Path(PATCH_ID) id: String
    ): Observable<Character>

    companion object {
        private const val QUERY_PAGE = "page"
        private const val PATCH_ID = "Id"
    }
}
