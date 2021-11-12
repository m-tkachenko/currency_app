package com.example.currency_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = Common.retrofitService

        recyclerview_for_days.setHasFixedSize(true)
        val adapterForDays = RecyclerAdapterForDays(service)
        recyclerview_for_days.adapter = adapterForDays
        val linearLayoutManager = recyclerview_for_days.layoutManager as LinearLayoutManager
        // TODO: Remove this
        var flagOnce = false

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = recyclerView.layoutManager?.itemCount
                // TODO: Find out how to search for last visible item
                val lastVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                Log.w("Item", lastVisibleItemPosition.toString())

                if(itemCount == lastVisibleItemPosition + 1) {
                    adapterForDays.onScrollUpdate()
                }
                else if (lastVisibleItemPosition == -1 && !flagOnce) {
                    adapterForDays.onScrollUpdate()
                    flagOnce = true
                }
            }
        }

        recyclerview_for_days.addOnScrollListener(scrollListener)
    }
}