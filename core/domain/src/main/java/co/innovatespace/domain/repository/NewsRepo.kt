package co.innovatespace.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import co.innovatespace.domain.model.NewsInt
import co.innovatespace.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
     fun getNews(country: String = "ng", q: String? = null, category: List<String>?): Flow<PagingData<NewsInt>>
     fun getFavoriteNews(): Pager<Int,NewsInt>
     fun getSources(q: String): Flow<PagingData<Source>>
     suspend fun fetchSources()
}