package co.innovatespace.data

import androidx.paging.*
import co.innovatespace.data.api.ApiService
import co.innovatespace.data.api.model.mapper.SourceMapper
import co.innovatespace.data.cache.Cache
import co.innovatespace.data.mediator.NewsMediator
import co.innovatespace.domain.model.NetworkUnavailableException
import co.innovatespace.domain.model.NewsInt
import co.innovatespace.domain.model.Source
import co.innovatespace.domain.model.throwAppropriateError
import co.innovatespace.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NewsRepository @Inject constructor(private val apiService: ApiService, private val cache: Cache ): NewsRepo {

    override fun getNews(country: String, q: String?, category: List<String>?): Flow<PagingData<NewsInt>> {
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
            remoteMediator = NewsMediator(cache = cache, apiService = apiService, query = q, country = country, category = category?.joinToString( ",")   )
        ){
            cache.selectAllNews()
        }.flow
    }

    override fun getFavoriteNews(): Pager<Int, NewsInt> {
        TODO("Not yet implemented")
    }

    override fun getSources(q: String): Flow<PagingData<Source>> {
        return  Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            //remoteMediator = SourceMediator(cache = cache, apiService = apiService)
        ){
            cache.selectAllSources(q)
        }.flow
    }

    override suspend fun fetchSources() {
        try{
            val apiResponse = apiService.getSources()
            val result = SourceMapper().mapListToDomain(apiResponse.results)
            cache.deleteAllSources()
            cache.storeSources(result)
        }catch (exception: IOException){
            throw NetworkUnavailableException(exception.message ?: "Request Failed")
        }catch (exception: HttpException) {
            throwAppropriateError(exception.code(), exception.message())
        }
    }
}