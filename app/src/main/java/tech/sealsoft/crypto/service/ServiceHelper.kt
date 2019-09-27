package tech.sealsoft.crypto.service

import android.content.Context
import android.util.Log
import tech.sealsoft.crypto.model.CoinEntity

object ServiceHelper {

    fun callAllCoins(context: Context): List<CoinEntity> {
        val requestService = ServiceBuilder.buildService(CoinService::class.java)
        val requestCall = requestService.getCoins()
        val response = requestCall.execute()
        if (response.isSuccessful) {
            Log.d("tag", response.body().toString())
            return response.body()?.data!!
        }

        return listOf()
    }
}