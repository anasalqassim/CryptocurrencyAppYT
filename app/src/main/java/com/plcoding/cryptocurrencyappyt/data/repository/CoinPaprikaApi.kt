package com.plcoding.cryptocurrencyappyt.data.repository

import retrofit2.Call
import retrofit2.http.GET

interface CoinPaprikaApi {

    @GET("/v1/coins")
   suspend fun getCoins()

}