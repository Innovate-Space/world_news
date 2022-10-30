package co.innovatespace.source.usecases

import co.innovatespace.domain.repository.NewsRepo
import javax.inject.Inject

class GetSources @Inject constructor(private val  newsRepository: NewsRepo){
    operator fun invoke() = newsRepository.getSources("")
}