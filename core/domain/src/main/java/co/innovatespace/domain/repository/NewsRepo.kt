package co.innovatespace.domain.repository

import androidx.paging.Pager
import co.innovatespace.domain.model.News

interface NewsRepo {
    suspend fun getNews(country: String = "ng", q: String? = null, category: List<String>?): Pager<Int,News>
    suspend fun getFavoriteNews(): Pager<Int,News>
}