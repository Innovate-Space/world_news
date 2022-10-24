package co.innovatespace.data.api.model

data class ApiNewsWrapper(
    val status : String?,
    val totalResults: Int?,
    val results: List<ApiNews>?,
    val nextPage: Int?
)
