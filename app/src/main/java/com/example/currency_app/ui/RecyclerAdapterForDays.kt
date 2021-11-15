package com.example.currency_app.ui

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_app.R
import com.example.currency_app.data.Currency
import com.example.currency_app.data.retrofit.RetrofitService
import kotlinx.android.synthetic.main.cardview_for_days_in_recyclerview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class RecyclerAdapterForDays(private val service: RetrofitService)
    : RecyclerView.Adapter<RecyclerAdapterForDays.ViewHolder>() {
    @RequiresApi(Build.VERSION_CODES.O)
    var mDate = LocalDate.now()
    var listCurrency : MutableList<Currency> = mutableListOf()

    init {
        Log.w("Date", "${mDate.toString()}")
        getCurrency(mDate.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_for_days_in_recyclerview, parent, false)

        return ViewHolder(view)
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position == (itemCount - 1)) {
            mDate = mDate.minusDays(1)
            Log.w("Date", "${mDate.toString()}")
            getCurrency(mDate.toString())
        }

        val mapItem = listCurrency[position]

        holder.connect(mapItem)
    }

    override fun getItemCount(): Int {
        return listCurrency.size
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun connect(item: Currency) {
            view.textView_for_day.text = "Day ${item.date}"
            view.recyclerview_for_currencies.adapter =
                RecyclerAdapterForCurrencies(item.rates, item.date)
        }
    }
}