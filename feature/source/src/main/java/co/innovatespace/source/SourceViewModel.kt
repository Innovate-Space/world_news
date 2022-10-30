package co.innovatespace.source

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.innovatespace.source.usecases.GetSources
import co.innovatespace.ui.presentation.UINews
import co.innovatespace.ui.presentation.UISource
import co.innovatespace.utility.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val getSource: GetSources, private val dispatchProvider: DispatchersProvider, private val savedStateHandle: SavedStateHandle) : ViewModel() {
   // val pagingDataFlow : Flow<PagingData<UISource>>

//    init {
//        pagingDataFlow = getSource().map { pagingData -> pagingData.map {  UISource(
//            id = it.id,
//            name = it.name,
//            description = it.description,
//            url = it.url,
//            category = it.category
//        )  } }.flowOn(dispatchProvider.default()).cachedIn(viewModelScope)
//    }
//
//
//    private fun searchRepo(queryString: String): Flow<PagingData<UINews>> {
//        TODO("I dey come normally")
//    }
}