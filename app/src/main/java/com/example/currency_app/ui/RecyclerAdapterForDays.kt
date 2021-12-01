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
import com.example.currency_app.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.cardview_for_days_in_recyclerview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class RecyclerAdapterForDays : RecyclerView.Adapter<RecyclerAdapterForDays.ViewHolder>() {

    private var mDate: LocalDate = LocalDate.now()
    var listCurrency = mutableListOf<Currency>()

    fun setList(list: List<Currency>){
        this.listCurrency = list.toMutableList()
        notifyDataSetChanged()
    }

//    init {
//        Log.w("Date", "$mDate")
//        getCurrency(mDate.toString())
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

//    private fun getCurrency(date: String) {
//        service.getCurrencyMap(date).enqueue(object : Callback<Currency> {
//            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
//
//                response.body()?.let { listCurrency.add(it) }
//                notifyDataSetChanged()
//
//                Log.w("RetrofitResponse", "$response")
//            }
//
//            override fun onFailure(call: Call<Currency>, t: Throwable) {
//                Log.w("RetrofitResponse", "$t")
//            }
//
//        })
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listItem = listCurrency[position]

        holder.binding.recyclerviewForDays.textView_for_day.text = "Dat ${listItem.date}"
        holder.binding.recyclerviewForDays.recyclerview_for_currencies.adapter =
            RecyclerAdapterForCurrencies(listItem.rates, listItem.date)

//        if(position == (itemCount - 1)) {
//            mDate = mDate.minusDays(1)
//            Log.w("Date", "$mDate")
//            getCurrency(mDate.toString())
//        }
//
//
    }

    override fun getItemCount(): Int {
        return listCurrency.size
    }

    inner class ViewHolder(val binding : ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}