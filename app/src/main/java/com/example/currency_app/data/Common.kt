package com.example.currency_app.data

import com.example.currency_app.data.retrofit.RetrofitClient
import com.example.currency_app.data.retrofit.RetrofitService
import retrofit2.create

object Common {
    private val BASE_URL = "http://data.fixer.io/api/"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}