package co.innovatespace.domain.repository

import androidx.paging.Pager
import co.innovatespace.domain.model.News
import co.innovatespace.domain.model.NewsInt

interface NewsRepo {
    suspend fun getNews(country: String = "ng", q: String? = null, category: List<String>?): Pager<Int,NewsInt>
    suspend fun getFavoriteNews(): Pager<Int,NewsInt>
}