package co.innovatespace.data.di

import co.innovatespace.data.api.ApiConstants
import co.innovatespace.data.api.ApiService
import co.innovatespace.data.api.interceptor.AuthenticationInterceptor
import co.innovatespace.data.api.interceptor.LoggingInterceptor
import co.innovatespace.data.api.interceptor.NetworkStatusInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): ApiService {
        return builder.build().create(ApiService::class.java)
    }

    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor,
        authenticationInterceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
        .addInterceptor(networkStatusInterceptor)
        .addInterceptor(authenticationInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
    }
}