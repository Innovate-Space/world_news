package co.innovatespace.source.usecases

import co.innovatespace.domain.repository.NewsRepo
import javax.inject.Inject

class FetchNetworkSource @Inject constructor(private val  newsRepository: NewsRepo){
    suspend operator fun invoke() = newsRepository.fetchSources()
}