package com.example.currency_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import com.example.currency_app.R
import com.example.currency_app.data.Common
import com.example.currency_app.data.Currency
import com.example.currency_app.data.retrofit.RetrofitService
import com.example.currency_app.ui.RecyclerAdapterForCurrencies
import com.example.currency_app.ui.RecyclerAdapterForDays
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cardview_for_days_in_recyclerview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var service: RetrofitService
    var adapterForDays: RecyclerAdapterForDays? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = Common.retrofitService
        recyclerview_for_days.setHasFixedSize(true)

        service.getCurrencyMap().enqueue(object : Callback<Currency>{
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                adapterForDays = response.body()?.let { RecyclerAdapterForDays(it) }
                adapterForDays?.notifyDataSetChanged()
                recyclerview_for_days.adapter = adapterForDays

                Log.w("RetrofitResponse", "${response}")
            }

            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Log.w("RetrofitFail", "$t")
            }

        })
    }
}