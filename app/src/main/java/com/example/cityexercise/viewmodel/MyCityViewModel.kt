package com.example.cityexercise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cityexercise.model.network.WeatherService
import com.example.cityexercise.model.weather.WeatherData
import kotlinx.coroutines.*

class MyCityViewModel(application: Application):AndroidViewModel(application) {
    val APP_ID="82a76c68dbc31eaf1415dafd7e28a0c2"
    val weatherService=WeatherService.getWeatherDataService()
    val job: Job?=null
    val exceptionHandler= CoroutineExceptionHandler{coroutineContext, throwable ->
        onError("Exception:${throwable.localizedMessage}")

    }

    val weather = MutableLiveData<WeatherData>()
    val weatherLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(city: String){
        fetchWeather(city)
    }

    private fun fetchWeather(city:String) {
        loading.value=true

        CoroutineScope(Dispatchers.IO +exceptionHandler).launch {
            val response=weatherService.getWeatherData(city,APP_ID)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    weather.value=response.body()
                    weatherLoadError.value=null
                    loading.value=false
                }else{
                    onError("Error:${response.message()}")
                }
            }
        }


    }




    private fun onError(message: String) {
        weatherLoadError.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}