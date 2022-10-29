package co.innovatespace.domain.model

data class News(
    override val title: String,
    override val description: String,
    override val content: String,
    override val id: Long,
    override val link: String,
    override val imageUrl: String,
    override val pubDate: String,
    override val category: String
) : NewsInt

interface NewsInt {
    val id: Long
    val title: String
    val description: String
    val content: String
    val link: String
    val imageUrl: String
    val pubDate: String
    val category: String
}
