package co.innovatespace.source.frag

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.innovatespace.source.usecases.GetSources
import co.innovatespace.ui.presentation.UISource
import co.innovatespace.utility.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SourceCategoryViewModel @Inject constructor(private val getSource: GetSources, private val dispatchProvider: DispatchersProvider, private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val pagingDataFlow : Flow<PagingData<UISource>>

    init {
        val key = savedStateHandle.getStateFlow(ARG_OBJECT, "")
        pagingDataFlow = key.flatMapLatest { getSource(it).map { pagingData -> pagingData.map { it -> UISource(
            id = it.id,
            name = it.name,
            description = it.description,
            url = it.url,
            category = it.category
        )  } }.flowOn(dispatchProvider.default()).cachedIn(viewModelScope) }
    }
}