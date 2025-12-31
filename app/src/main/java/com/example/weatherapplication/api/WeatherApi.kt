package com.example.weatherapplication.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apikey : String,
        @Query("q") city : String,
        @Query("aqi") aqi: String = "no"
    ) : Response<WeatherModel>

}