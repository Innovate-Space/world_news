package co.innovatespace.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import co.innovatespace.data.api.ApiService
import co.innovatespace.data.cache.dao.NewsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsMediator @Inject constructor(val apiService: ApiService, newsDao: NewsDao) {
    @OptIn(ExperimentalPagingApi::class)
    inner class Mediator: RemoteMediator<Int, Any>() {
        override suspend fun load(loadType: LoadType, state: PagingState<Int, Any>): MediatorResult {
            TODO("Not yet implemented")
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
}