package co.innovatespace.data.cache

import co.innovatespace.data.cache.dao.CategoryDao
import co.innovatespace.data.cache.dao.NewsDao
import co.innovatespace.data.cache.dao.SourceDao
import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheSource
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val newsDao: NewsDao,
    private val sourceDao: SourceDao,
    private val categoryDao: CategoryDao
): Cache {
    override suspend fun storeNewsList(newsList: List<CacheNews>) =  newsDao.insert(newsList)

    override suspend fun storeCategoryList(categories: List<CacheCategory>)  = categoryDao.insert(categories)

    override suspend fun storeSources(sources: List<CacheSource>) = sourceDao.insert(sources)
}