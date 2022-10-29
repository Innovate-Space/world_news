package co.innovatespace.home.usecases

import co.innovatespace.domain.repository.NewsRepo
import javax.inject.Inject

class GetNews @Inject constructor(private val  newsRepository: NewsRepo){
    operator fun invoke() = newsRepository.getNews(category = null,)
}