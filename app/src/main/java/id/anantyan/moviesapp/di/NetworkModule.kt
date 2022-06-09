package id.anantyan.moviesapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.anantyan.moviesapp.data.api.MoviesApi
import id.anantyan.moviesapp.utils.Constant.BASE_URL
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTmdb(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit { // untuk TMDB Api
        return Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().apply {
            setLenient()
            registerTypeAdapter(Date::class.java, JsonDeserializer { jsonElement, _, _ ->
                Date(jsonElement.asJsonPrimitive.asLong)
            })
        }.create()
    }

    @Singleton
    @Provides
    fun providerHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addNetworkInterceptor(httpLoggingInterceptor)
            cookieJar(JavaNetCookieJar(CookieManager()))
            connectTimeout(15, TimeUnit.MINUTES)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}