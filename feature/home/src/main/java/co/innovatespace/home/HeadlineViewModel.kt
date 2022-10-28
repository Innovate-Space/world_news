package co.innovatespace.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import co.innovatespace.home.usecases.GetNews
import co.innovatespace.ui.presentation.UINews
import co.innovatespace.utility.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(private val getNews: GetNews, private val dispatchProvider: DispatchersProvider, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    lateinit var pagingDataFlow : Flow<PagingData<UINews>>
    init {
        println("I executed")
        val result = getNews()
        println(result)
    }
}