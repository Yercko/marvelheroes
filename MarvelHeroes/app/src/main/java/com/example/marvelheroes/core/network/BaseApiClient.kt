package com.example.marvelheroes.core.network

import com.example.marvelheroes.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

open class BaseApiClient<T>(private val classT: Class<T>) {

    companion object{
        const val CONNECTION_TIMEOUT: Long = 180L
        const val READ_TIMEOUT: Long = 180L
        const val WRITE_TIMEOUT: Long = 180L
    }

    open fun getApiClient(): T {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_ENDPOINT)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }


        val retrofit = retrofitBuilder.build()
        return retrofit.create(classT)
    }
    class ApiKeyInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var original = chain.request()
            val md5 = MessageDigest.getInstance("MD5")
            val tsLong = System.currentTimeMillis() / 1000
            val ts = tsLong.toString()
            try {
                val hash = md5.digest((ts+BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toByteArray(charset("UTF-8")))
                val apiKeyHash: String = hash.toHexString()
                val url = original.url.newBuilder()
                        .addQueryParameter("ts", ts)
                        .addQueryParameter("apikey",BuildConfig.PUBLIC_KEY)
                        .addQueryParameter("hash",apiKeyHash)
                        .build()
                original = original.newBuilder().url(url).build()
            } catch (e: UnsupportedEncodingException) {
                 return chain.proceed(original)
            }


            return chain.proceed(original)
        }

        @ExperimentalUnsignedTypes // just to make it clear that the experimental unsigned types are used
        fun ByteArray.toHexString() = asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }
    }

}