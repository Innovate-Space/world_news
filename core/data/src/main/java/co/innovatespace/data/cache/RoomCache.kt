package co.innovatespace.data.cache

import androidx.paging.PagingSource
import co.innovatespace.data.cache.dao.CategoryDao
import co.innovatespace.data.cache.dao.KeyDao
import co.innovatespace.data.cache.dao.NewsDao
import co.innovatespace.data.cache.dao.SourceDao
import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheRemoteKeys
import co.innovatespace.data.cache.model.CacheSource
import co.innovatespace.domain.model.News
import co.innovatespace.domain.model.NewsInt
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val newsDao: NewsDao,
    private val sourceDao: SourceDao,
    private val categoryDao: CategoryDao,
    private val keyDao: KeyDao
): Cache {
    override suspend fun storeNewsList(newsList: List<CacheNews>) =  newsDao.insert(newsList)

    override suspend fun storeCategoryList(categories: List<CacheCategory>)  = categoryDao.insert(categories)

    override suspend fun storeSources(sources: List<CacheSource>) = sourceDao.insert(sources)
    override suspend fun deleteAll()  = newsDao.clearAll()

    override fun selectAllNews(): PagingSource<Int, NewsInt> = newsDao.selectAll() as PagingSource<Int, NewsInt>
    override suspend fun cacheKey(key: CacheRemoteKeys) = keyDao.insert(key)

    override suspend fun deleteAllKey() = keyDao.clearAll()

    override suspend fun getKey(id: Long): CacheRemoteKeys? = keyDao.getKey(1)
}