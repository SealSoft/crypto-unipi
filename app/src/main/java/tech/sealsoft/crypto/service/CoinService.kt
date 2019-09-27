package tech.sealsoft.crypto.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import tech.sealsoft.crypto.model.CoinEntity
import tech.sealsoft.crypto.model.CoinEntityResponse

interface CoinService {

    @GET("assets/{id}?limit=5")
    fun getCoin(@Path("id") id: String): Call<CoinEntity>

    @GET("assets")
    fun getCoins(): Call<CoinEntityResponse>

}