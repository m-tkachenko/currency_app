package com.example.currency_app.data

import com.example.currency_app.data.retrofit.RetrofitService

class Repository(private val service: RetrofitService, val date: String) {
    fun getData() = service.getCurrencyMap(date)
}