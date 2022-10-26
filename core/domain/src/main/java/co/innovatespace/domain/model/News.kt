package co.innovatespace.domain.model

data class News(override val title: String, override val description: String, override val content: String,override val  id: Long): NewsInt

interface NewsInt{
    val  id: Long
    val title: String
    val description: String
    val content: String
}
