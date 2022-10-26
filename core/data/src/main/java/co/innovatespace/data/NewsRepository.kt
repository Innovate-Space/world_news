package co.innovatespace.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import co.innovatespace.data.api.ApiService
import co.innovatespace.data.cache.Cache
import co.innovatespace.data.mediator.NewsMediator
import co.innovatespace.domain.model.News
import co.innovatespace.domain.model.NewsInt
import co.innovatespace.domain.repository.NewsRepo
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService, private val cache: Cache ): NewsRepo {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getNews(country: String, q: String?, category: List<String>?): Pager<Int, NewsInt> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = NewsMediator(cache = cache, apiService = apiService, query = q, country = country, category = category?.joinToString( ",")   )
        ){
            cache.selectAllNews()
        }
    }

    override suspend fun getFavoriteNews(): Pager<Int, NewsInt> {
        TODO("Not yet implemented")
    }
}