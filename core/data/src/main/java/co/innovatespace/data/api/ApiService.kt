package co.innovatespace.data.api

import co.innovatespace.data.api.model.ApiNewsWrapper
import co.innovatespace.data.api.model.ApiSourceWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(value = ApiConstants.HEADLINE_ENDPOINT)
    suspend fun getNewsHeadLines(
        @Query(ApiParameters.PAGE) page: Int = 0,
        @Query(ApiParameters.COUNTRY) language: String ="ng",
        @Query(ApiParameters.CATEGORY) category: String? = null,
        @Query(ApiParameters.PAGE_QUERY) query: String? = null,
    ): ApiNewsWrapper

    @GET(value = ApiConstants.SOURCE_ENDPOINT)
    suspend fun getSources(): ApiSourceWrapper
}