package com.example.conversormoeda.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class CurrencyRate(
    val bid: String
)

interface AwesomeApiService {
    @GET("last/{pair}")
    fun getExchangeRate(@Path("pair") pair: String): retrofit2.Call<Map<String, ExchangeRate>>
}



