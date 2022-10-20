package co.innovatespace.data.api.interceptor

import co.innovatespace.data.api.ConnectionManager
import co.innovatespace.domain.model.NetworkUnavailableException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @author Kingsley etoka
 * email etokakingsley@gmail.com
 * Checks if the device is connected to the internet.
 * If the device is not connected, It throws a [NetworkUnavailableException] and cancels the request
 * The reason we have implemented this feature is to avoid wasting the users time and optimizing performance
 */
class NetworkStatusInterceptor @Inject constructor(private val connectionManager: ConnectionManager):
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return when{
            connectionManager.isConnected() -> chain.proceed(chain.request())
            else -> throw NetworkUnavailableException("Please check your internet connectivity")
        }
    }
}