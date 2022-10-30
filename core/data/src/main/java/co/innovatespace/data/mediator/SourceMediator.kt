package co.innovatespace.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import co.innovatespace.data.api.ApiService
import co.innovatespace.data.api.model.mapper.SourceMapper
import co.innovatespace.data.cache.Cache
import co.innovatespace.domain.model.Source
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SourceMediator constructor(private val apiService: ApiService, val cache: Cache): RemoteMediator<Int, Source>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Source>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> {
                return  MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> null
        }

        return try{
            val apiResponse = apiService.getSources()
            val result = SourceMapper().mapListToDomain(apiResponse.results)
            cache.storeSources(result)
            MediatorResult.Success(true)
        }catch (exception: IOException){
            MediatorResult.Error(exception)
        }catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}