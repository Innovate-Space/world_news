package co.innovatespace.data.api.model

data class ApiNews(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val content: String?,
    val publishedAt : String,
    val source: ApiSource?
)