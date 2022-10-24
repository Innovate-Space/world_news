package co.innovatespace.ui.presentation

data class UINews(
    val id: Long,
    val title: String,
    val author: String?,
    val link: String,
    val description: String,
    val content: String,
    val pubDate: String,
    val image: String,
)
