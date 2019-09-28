package tech.sealsoft.crypto.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import tech.sealsoft.crypto.model.MarketEntityResponse

interface MarketService {
    @GET("assets/{coinId}/markets?limit=10")
    fun getMarketsOfCoin(@Path("coinId") coinId: String): Call<MarketEntityResponse>
}