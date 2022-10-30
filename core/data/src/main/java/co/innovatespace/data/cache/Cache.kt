package co.innovatespace.data.cache

import androidx.paging.PagingSource
import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheRemoteKeys
import co.innovatespace.domain.model.NewsInt
import co.innovatespace.domain.model.Source

interface Cache {
    suspend fun storeNewsList(newsList : List<CacheNews>)
    suspend fun storeCategoryList(categories : List<CacheCategory>)
    suspend fun storeSources(sources : List<Source>)
    fun selectAllSources(): PagingSource<Int, Source>
    suspend fun deleteAllSources()
    suspend fun deleteAll()
    fun selectAllNews(): PagingSource<Int, NewsInt>

    suspend fun cacheKey(key: CacheRemoteKeys)
    suspend fun  deleteAllKey()
    suspend fun getKey(id: Long = 1): CacheRemoteKeys?
}