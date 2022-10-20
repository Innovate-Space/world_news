package co.innovatespace.data.api

import co.innovatespace.data.api.model.ApiNewsWrapper
import co.innovatespace.data.api.model.ApiSourceWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(value = ApiConstants.HEADLINE_ENPOINT)
    suspend fun getNewsHeadLines(
        @Query(ApiParameters.PAGE_SIZE) pageSize: Int,
        @Query(ApiParameters.PAGE) page: Int,
        @Query(ApiParameters.LANGUAGE) language: String ="en",
        @Query(ApiParameters.SORT_BY) sortBy: String?,
    ): ApiNewsWrapper

    @GET(value = ApiConstants.SOURCE_ENDPOINT)
    suspend fun getSources(): ApiSourceWrapper
}