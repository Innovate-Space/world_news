package co.innovatespace.home

import androidx.lifecycle.ViewModel
import co.innovatespace.home.usecases.GetNews
import co.innovatespace.utility.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(private val getNews: GetNews, private val dispatchProvider: DispatchersProvider, ) : ViewModel() {
    init {
        println("I executed")
        val result = getNews()
        println(result)
    }
}