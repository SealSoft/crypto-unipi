package tech.sealsoft.crypto.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    const val SERVER_URL = "https://api.coincap.io/v2/"

    private var selectedUrl = SERVER_URL
    // Create okHttp Client. 25 seconds timeout tbd
    private val okHttp = OkHttpClient.Builder().callTimeout(20000, TimeUnit.SECONDS)
        .readTimeout(2000, TimeUnit.SECONDS)


    // Retrofit builder with GSON converter
    private val builder = Retrofit.Builder().baseUrl(selectedUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttp.build())

    // instantiate retrofit
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}