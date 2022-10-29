package co.innovatespace.data.api.model.mapper

import co.innovatespace.data.api.model.ApiNews
import co.innovatespace.data.cache.model.CacheNews
import javax.inject.Inject

class NewsMapper @Inject constructor(): ApiMapper<ApiNews, CacheNews > {
    override fun mapToDomain(apiEntity: ApiNews): CacheNews {
        return CacheNews(
            title = apiEntity.title.orEmpty(),
            description = apiEntity.description.orEmpty(),
            content = apiEntity.content.orEmpty(),
            link = apiEntity.link.orEmpty(),
            imageUrl = apiEntity.imageUrl.orEmpty(),
            pubDate = apiEntity.pubDate.orEmpty(),
            category = apiEntity.category?.joinToString(",") ?: ""
        )
    }

    fun mapListToDomain(apiEntityList: List<ApiNews>?): List<CacheNews> {
        return apiEntityList?.map { mapToDomain(it) } ?: listOf()
    }
}