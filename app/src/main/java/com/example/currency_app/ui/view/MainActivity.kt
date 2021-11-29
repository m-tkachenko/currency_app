package com.example.currency_app.ui.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.currency_app.R
import com.example.currency_app.data.Common
import com.example.currency_app.data.retrofit.RetrofitService
import com.example.currency_app.ui.RecyclerAdapterForDays
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var service: RetrofitService
    private lateinit var vm: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = Common.retrofitService
        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        recyclerview_for_days.setHasFixedSize(true)
        val adapterForDays = RecyclerAdapterForDays(service)
        recyclerview_for_days.adapter = adapterForDays

        button_up_to.setOnClickListener {
            recyclerview_for_days.scrollToPosition(0)
        }
    }
}