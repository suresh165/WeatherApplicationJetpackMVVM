package com.example.weatherapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.api.Constant
import com.example.weatherapplication.api.NetworkResponse
import com.example.weatherapplication.api.RetrofitInstance
import com.example.weatherapplication.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()

    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                    //Log.i("Response", response.body().toString())
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                    //Log.i("Error", response.message())
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

        }

    }
}