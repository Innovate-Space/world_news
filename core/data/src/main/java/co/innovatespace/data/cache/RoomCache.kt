package co.innovatespace.data.cache

import androidx.paging.PagingSource
import co.innovatespace.data.cache.dao.CategoryDao
import co.innovatespace.data.cache.dao.KeyDao
import co.innovatespace.data.cache.dao.NewsDao
import co.innovatespace.data.cache.dao.SourceDao
import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheRemoteKeys
import co.innovatespace.domain.model.NewsInt
import co.innovatespace.domain.model.Source
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val newsDao: NewsDao,
    private val sourceDao: SourceDao,
    private val categoryDao: CategoryDao,
    private val keyDao: KeyDao
): Cache {
    override suspend fun storeNewsList(newsList: List<CacheNews>) =  newsDao.insert(newsList)

    override suspend fun storeCategoryList(categories: List<CacheCategory>)  = categoryDao.insert(categories)

    override suspend fun storeSources(sources: List<Source>) = sourceDao.insert(sources)
    override fun selectAllSources(q: String): PagingSource<Int, Source>  = sourceDao.selectAll(q)
    override suspend fun deleteAllSources() = sourceDao.clearAll()

    override suspend fun deleteAll()  = newsDao.clearAll()

    override fun selectAllNews(): PagingSource<Int, NewsInt> = newsDao.selectAll() as PagingSource<Int, NewsInt>
    override suspend fun cacheKey(key: CacheRemoteKeys) = keyDao.insert(key)

    override suspend fun deleteAllKey() = keyDao.clearAll()

    override suspend fun getKey(id: Long): CacheRemoteKeys? = keyDao.getKey(1)
}