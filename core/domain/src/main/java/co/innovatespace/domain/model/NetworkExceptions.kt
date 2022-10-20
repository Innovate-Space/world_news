package co.innovatespace.domain.model

import java.io.IOException

sealed class NetworkExceptions( message: String , statusCode: Int = -1): IOException( message )

class NetworkUnavailableException(message: String = "Network Unavailable Exception", statusCode: Int = StatusCode.UNKNOWN_STATUS) : NetworkExceptions(message)

class UnAuthorizedException(message: String ="User is unauthorized", statusCode: Int = StatusCode.UNAUTHORIZED_STATUS): NetworkExceptions(message)


object StatusCode{
    const val SUCCESS_STATUS = 200
    const val UNAUTHORIZED_STATUS =  401
    const val NOT_FOUND_STATUS = 404
    const val UNKNOWN_STATUS = -1
}