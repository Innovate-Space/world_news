package co.innovatespace.data.cache

import androidx.paging.PagingSource
import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheSource
import co.innovatespace.domain.model.News
import co.innovatespace.domain.model.NewsInt

interface Cache {
    suspend fun storeNewsList(newsList : List<CacheNews>)
    suspend fun storeCategoryList(categories : List<CacheCategory>)
    suspend fun storeSources(sources : List<CacheSource>)
    suspend fun deleteAll()
    fun selectAllNews(): PagingSource<Int, NewsInt>
}