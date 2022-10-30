package co.innovatespace.domain.model

import java.io.IOException

sealed class NetworkExceptions( message: String ): IOException( message )

class NetworkUnavailableException(message: String = "Network Unavailable Exception", statusCode: Int = StatusCode.UNKNOWN_STATUS) : NetworkExceptions(message)

class UnAuthorizedException(message: String ="User is unauthorized", statusCode: Int = StatusCode.UNAUTHORIZED_STATUS): NetworkExceptions(message)

class NotFoundException(message: String ="User is unauthorized", statusCode: Int = StatusCode.NOT_FOUND_STATUS): NetworkExceptions(message)

object StatusCode{
    const val SUCCESS_STATUS = 200
    const val UNAUTHORIZED_STATUS =  401
    const val NOT_FOUND_STATUS = 404
    const val UNKNOWN_STATUS = -1
}

enum class ErrorStatusCode(val code: Int){
    Unauthorized(401),
    NotFound(404)
}

fun throwAppropriateError( statusCode: Int, message: String): Nothing {
   return  when(statusCode){
       ErrorStatusCode.Unauthorized.code -> throw UnAuthorizedException( message )
       ErrorStatusCode.NotFound.code -> throw NotFoundException(message)
       else-> throw NetworkUnavailableException( message)
   }
}