package co.innovatespace.data.api.model

import com.google.gson.annotations.SerializedName

data class ApiNews(
    val creator: List<String?>?,
    val title: String?,
    val description: String?,
    val content: String?,
    val link: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("video_url")
    val videoUrl: String?,
    val pubDate : String?,
    @SerializedName("source_id")
    val sourceId: String?,
    val category : List<String?>?
)