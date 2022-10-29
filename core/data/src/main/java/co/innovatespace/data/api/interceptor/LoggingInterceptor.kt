package co.innovatespace.data.api.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

/**
 * @author Kingsley etoka
 * email etokakingsley@gmail.com
 * Logs all the network request output to the console to enable us debug easily.
 * This class is only here to make testing a breeze.
 */
class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        println("-------x-------")
       println(message)
        println("-------p-------")
    }
}