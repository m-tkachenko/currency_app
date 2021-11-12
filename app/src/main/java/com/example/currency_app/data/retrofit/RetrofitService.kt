package com.example.currency_app.data.retrofit

import com.example.currency_app.data.Currency
import com.example.currency_app.data.Rates
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("latest?access_key=f9ad8ea054daa3a0b2f7c1da207ffa22")
    fun getCurrencyMap(): Call<Currency>
}