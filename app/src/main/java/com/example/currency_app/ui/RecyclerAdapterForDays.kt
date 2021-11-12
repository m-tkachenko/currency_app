package com.example.currency_app.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_app.R
import com.example.currency_app.data.Currency
import kotlinx.android.synthetic.main.cardview_for_days_in_recyclerview.view.*

class RecyclerAdapterForDays(private val currenciesMap: Currency)
    : RecyclerView.Adapter<RecyclerAdapterForDays.ViewHolder>() {

//    var date: String? = null
//    var listCurrency : MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_for_days_in_recyclerview, parent, false)

        return ViewHolder(view)
    }

//    fun getCurrency(date: String) {
//        // update list currency with new currency
//        // update recycler
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mapItem = currenciesMap.date

        holder.connect(mapItem)
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//        val dayTextView: TextView = view.findViewById(R.id.textView_for_day)

        fun connect(item: Any?) {
            view.textView_for_day.text = "Day ${item}"
            view.recyclerview_for_currencies.adapter =
                RecyclerAdapterForCurrencies(currenciesMap.rates, currenciesMap.date)
        }
    }
}