package com.example.currency_app.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currency_app.data.Currency
import com.example.currency_app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel(){
    val dataList = MutableLiveData<Currency>()
    val error = MutableLiveData<String>()

    fun getData() {
        val response = repository.getData()

        response.enqueue(object : Callback<Currency> {
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                dataList.postValue(response.body())
            }

            override fun onFailure(call: Call<Currency>, t: Throwable) {
                error.postValue(t.message)
            }

        })
    }
}