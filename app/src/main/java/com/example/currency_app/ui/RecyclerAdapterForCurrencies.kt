package com.example.currency_app.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_app.R
import com.example.currency_app.ui.view.CurrencyActivity

class RecyclerAdapterForCurrencies(private val currencyRates: MutableMap<String, Double>,
                                   private val day: String)
    : RecyclerView.Adapter<RecyclerAdapterForCurrencies.ViewHolder>() {

    var listRates: List<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ViewHolder {

        listRates = currencyRates.toList().map { "${it.first} : ${it.second}" }
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_for_currencies_in_recyclerview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textviewForCurrencyInfo.text = listRates?.get(position) ?: "Sorry"
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CurrencyActivity::class.java).apply {
                putExtra("Day", day)
                putExtra("CurrencyRate", listRates?.get(position))
            }
            startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount() = 20

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textviewForCurrencyInfo: TextView = view.findViewById(R.id.textView_for_currency_info)
    }
}