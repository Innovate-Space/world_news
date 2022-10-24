package co.innovatespace.data.api.interceptor

import co.innovatespace.data.BuildConfig
import co.innovatespace.data.api.ApiParameters
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.createAuthenticationRequest(BuildConfig.API_KEY)
        return chain.proceed(request)
    }

    private fun Interceptor.Chain.createAuthenticationRequest(token: String) : Request {
        return request().newBuilder().addHeader(ApiParameters.AUTH_HEADER, ApiParameters.TOKEN_TYPE + token).build()
    }
}