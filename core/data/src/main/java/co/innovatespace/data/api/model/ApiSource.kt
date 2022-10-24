package co.innovatespace.data.api.model

data class ApiSource(
    val id: String?,
    val name: String?,
    val description : String?,
    val url: String?,
    val category : List<String?>?,
    val language :  List<String?>?,
    val country : List<String?>?
)
