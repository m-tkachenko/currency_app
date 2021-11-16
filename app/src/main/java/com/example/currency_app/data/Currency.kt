package com.example.currency_app.data

import kotlinx.serialization.Serializable

@Serializable
data class Currency( // CurrencyResponse
    val base: String,
    val date: String,
    val rates: MutableMap<String, Double>,
    val success: Boolean,
    val timestamp: Int
)