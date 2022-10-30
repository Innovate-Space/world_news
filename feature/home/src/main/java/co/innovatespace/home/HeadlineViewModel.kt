package co.innovatespace.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.innovatespace.home.usecases.GetNews
import co.innovatespace.ui.presentation.UINews
import co.innovatespace.utility.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(private val getNews: GetNews, private val dispatchProvider: DispatchersProvider, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val pagingDataFlow : Flow<PagingData<UINews>>

    init {
        pagingDataFlow = getNews().map { pagingData -> pagingData.map {  UINews(
            id = it.id,
            title = it.title,
            content = it.content,
            image = it.imageUrl,
            pubDate = it.pubDate,
            description = it.description,
            link = it.link,
            author = ""
        )  } }.flowOn(dispatchProvider.default()).cachedIn(viewModelScope)
    }


    private fun searchRepo(queryString: String): Flow<PagingData<UINews>> {
        TODO("I dey come normally")
    }
}