package co.innovatespace.source

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.innovatespace.source.usecases.FetchNetworkSource
import co.innovatespace.source.usecases.GetSources
import co.innovatespace.ui.createExceptionHandler
import co.innovatespace.ui.presentation.UINews
import co.innovatespace.ui.presentation.UISource
import co.innovatespace.utility.DispatchersProvider
import co.innovatespace.utility.Event
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

    fun fetchData() {
        val exceptionHandler = viewModelScope.createExceptionHandler{onFailure(it)}
        _viewState.value = viewState.value.copy(isLoading = true, hasError = false)
        viewModelScope.launch(exceptionHandler) {
            withContext(dispatchProvider.io()){
                fetchSources()
                _viewState.value = viewState.value.copy(isLoading = false)
            }
        }
    }

    private fun onFailure(failure: Throwable) {
        //Feel free to traverse the error here and respond differently to different error types if you wish
        _viewState.value = viewState.value.copy(isLoading = false, failure = Event(failure), hasError = true)
    }
}