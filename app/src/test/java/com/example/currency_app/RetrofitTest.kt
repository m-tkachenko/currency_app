package com.example.currency_app

import com.example.currency_app.data.Common
import com.example.currency_app.data.retrofit.RetrofitService
import org.junit.Test
import org.junit.Assert.*

class RetrofitTest {
    @Test
    fun `can get method` () {
        val service: RetrofitService = Common.retrofitService
        val response = service.getCurrencyMap("2021-11-15").execute()

        assertTrue(response.code() == 200)
    }
}