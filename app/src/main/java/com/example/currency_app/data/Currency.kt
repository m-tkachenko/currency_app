package com.example.currency_app.data

import kotlinx.serialization.Serializable

@Serializable
data class Currency( // CurrencyResponse
    val base: String,
    val date: String,
    val rates: MutableMap<String, Double>,
    val success: Boolean,
    val timestamp: Int
) {

}

//data class CurrencyViewModel(
//    val base: String,
//    val date: String,
//    val rates: MutableMap<String, Double>,
//    val success: Boolean,
//    val timestamp: Int
//) {
//    val bs: String
//    val dt: String
//    val rt: List<MutableMap<String, Double>>
//
//    init {
//        this.bs = base
//        this.dt = date
//        this.rt = rates.toList().map { mutableMapOf(it.first to it.second) }
//    }
//}