package com.example.currency_app.data.retrofit

import com.example.currency_app.data.Currency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("{date}?access_key=65fa00d2079cad7e579b1a12d71f14b0")
    fun getCurrencyMap(
        @Path("date") date: String
    ): Call<Currency>
}