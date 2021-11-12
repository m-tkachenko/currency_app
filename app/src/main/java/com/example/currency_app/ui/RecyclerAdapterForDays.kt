package com.example.currency_app.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_app.R
import com.example.currency_app.data.Currency
import com.example.currency_app.data.retrofit.RetrofitService
import kotlinx.android.synthetic.main.cardview_for_days_in_recyclerview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RecyclerAdapterForDays(private val service: RetrofitService)
    : RecyclerView.Adapter<RecyclerAdapterForDays.ViewHolder>() {
    // TODO: Change this on Date type instead of String
    var date: String = "2021-11-10"
    var listCurrency : MutableList<Currency> = mutableListOf()

    init {
        getCurrency(date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_for_days_in_recyclerview, parent, false)

        return ViewHolder(view)
    }

    fun onScrollUpdate() {
        getCurrency("2021-11-12")
    }

    fun getCurrency(date: String) {
        service.getCurrencyMap(date).enqueue(object : Callback<Currency> {
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {

                response.body()?.let { listCurrency.add(it) }
                notifyDataSetChanged()

                Log.w("RetrofitResponse", "${response}")
            }

            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Log.w("RetrofitFail", "$t")
            }

        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mapItem = listCurrency[position]

        holder.connect(mapItem)
    }

    override fun getItemCount(): Int {
        return listCurrency.size
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//        val dayTextView: TextView = view.findViewById(R.id.textView_for_day)

        fun connect(item: Currency) {
            view.textView_for_day.text = "Day ${item.date}"
            view.recyclerview_for_currencies.adapter =
                RecyclerAdapterForCurrencies(item.rates, item.date)
        }
    }
}