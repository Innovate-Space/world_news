package co.innovatespace.data.cache

import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheSource

interface Cache {
    suspend fun storeNewsList(newsList : List<CacheNews>)
    suspend fun storeCategoryList(categories : List<CacheCategory>)
    suspend fun storeSources(sources : List<CacheSource>)
}