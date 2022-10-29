package co.innovatespace.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import co.innovatespace.data.api.ApiService
import co.innovatespace.data.api.model.mapper.NewsMapper
import co.innovatespace.data.cache.Cache
import co.innovatespace.data.cache.dao.NewsDao
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.domain.model.NewsInt
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val NEWS_DATA_STARTING_PAGE_INDEX = 0;

@OptIn(ExperimentalPagingApi::class)
class NewsMediator  constructor(val apiService: ApiService,val cache: Cache , val query: String?, val country: String = "ng", val category: String? ): RemoteMediator<Int, NewsInt>()  {

        override suspend fun load(loadType: LoadType, state: PagingState<Int, NewsInt>): MediatorResult {
            //state.anchorPosition
            val page = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> {
                    return  MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    NEWS_DATA_STARTING_PAGE_INDEX
                }
            }
            try{
                val apiResponse = apiService.getNewsHeadLines(page ?: NEWS_DATA_STARTING_PAGE_INDEX ,country, category, query)
                val result = NewsMapper().mapListToDomain(apiResponse.results)
                cache.storeNewsList(result)
                return MediatorResult.Success(apiResponse.nextPage == null)
            }catch (exception: IOException){
                return MediatorResult.Error(exception)
            }catch (exception: HttpException) {
                return MediatorResult.Error(exception)
            }
        }

        override suspend fun initialize(): InitializeAction {
//            val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
//            return if (System.currentTimeMillis() - db.lastUpdated() >= cacheTimeout)
//            {
//                // Cached data is up-to-date, so there is no need to re-fetch
//                // from the network.
//                InitializeAction.SKIP_INITIAL_REFRESH
//            } else {
//                // Need to refresh cached data from network; returning
//                // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
//                // APPEND and PREPEND from running until REFRESH succeeds.
//                InitializeAction.LAUNCH_INITIAL_REFRESH
//            }
            return super.initialize()
        }
}