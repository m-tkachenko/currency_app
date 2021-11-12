package com.example.currency_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currency_app.R
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        val dayData = intent.getStringExtra("Day")
        val currencyRateData = intent.getStringExtra("CurrencyRate")

        textView_for_day_currency_screen.text = dayData
        textView_for_currency_info_in_currency_screen.text = currencyRateData
    }
}