package com.example.currency_app

import com.example.currency_app.data.Common
import com.example.currency_app.data.retrofit.RetrofitService
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import java.time.LocalDate

class RetrofitTest {
    lateinit var service: RetrofitService
    lateinit var date: LocalDate

    @Before
    fun before() {
        service = Common.retrofitService
        date = LocalDate.now()
    }

    @Test
    fun `check request succeed` () {
        val response = service.getCurrencyMap(date.toString()).execute()

        assertTrue(response.body()?.success ?: false)
    }

    @Test
    fun `check serialization` () {
        val response = service.getCurrencyMap(date.toString()).execute()

        assertEquals(response.body()?.date ?: "Error", "$date")
        assertTrue(response.body() != null)
    }

    @Test
    fun `check request failed` () {
        val response = service.getCurrencyMap("e").execute()

        assertTrue(response.body()?.success ?: "Error" == false)
    }
}