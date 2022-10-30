package co.innovatespace.source

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.innovatespace.source.usecases.FetchNetworkSource
import co.innovatespace.source.usecases.GetSources
import co.innovatespace.ui.presentation.UINews
import co.innovatespace.ui.presentation.UISource
import co.innovatespace.utility.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val fetchSources: FetchNetworkSource, private val dispatchProvider: DispatchersProvider, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _viewState :MutableStateFlow<SourceViewState> = MutableStateFlow(SourceViewState())
    val viewState: StateFlow<SourceViewState> get() = _viewState

    init {
        fetchData()
    }

    private fun fetchData() {
        _viewState.value = viewState.value.copy(isLoading = true)
        viewModelScope.launch {
            withContext(dispatchProvider.io()){
                fetchSources()
                _viewState.value = viewState.value.copy(isLoading = false)
            }
        }
    }
}