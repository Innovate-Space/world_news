package co.innovatespace.data.api.model.mapper

import co.innovatespace.data.api.model.ApiNews
import co.innovatespace.data.api.model.ApiSource
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.domain.model.Source
import javax.inject.Inject

class SourceMapper @Inject constructor(): ApiMapper<ApiSource,Source > {
    override fun mapToDomain(apiEntity: ApiSource): Source {
        return Source(
            name = apiEntity.name.orEmpty(),
            description = apiEntity.description.orEmpty(),
            url = apiEntity.url.orEmpty(),
            category = apiEntity.category?.joinToString(",") ?: ""
        )
    }

    fun mapListToDomain(apiEntityList: List<ApiSource>?): List<Source> {
        return apiEntityList?.map { mapToDomain(it) } ?: listOf()
    }
}