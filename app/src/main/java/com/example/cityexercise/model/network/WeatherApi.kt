package com.example.cityexercise.model.network

import com.example.cityexercise.model.weather.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //https://api.openweathermap.org/data/2.5/weather?q=Kolkata&appid=82a76c68dbc31eaf1415dafd7e28a0c2

    @GET("weather")
    suspend fun getWeatherData(@Query("q")city:String,@Query("appid")appId:String):Response<WeatherData>

}