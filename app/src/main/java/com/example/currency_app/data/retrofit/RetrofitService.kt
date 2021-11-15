package com.example.currency_app.data.retrofit

import com.example.currency_app.data.Currency
import com.example.currency_app.data.Rates
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface RetrofitService {
    @GET("{date}?access_key=450cbb708d09e9e101cfdcb5e916e72b")
    fun getCurrencyMap(
        @Path("date") date: String
    ): Call<Currency>
}