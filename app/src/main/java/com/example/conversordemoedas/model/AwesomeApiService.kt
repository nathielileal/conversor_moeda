package com.example.conversordemoedas.model

import com.example.conversordemoedas.model.ExchangeRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AwesomeApiService {
    @GET("last/{pair}")
    fun getExchangeRate(@Path("pair") pair: String): Call<Map<String, ExchangeRate>>
}