package com.example.cityexercise.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.cityexercise.R
import com.example.cityexercise.model.weather.WeatherData
import com.example.cityexercise.viewmodel.MyCityViewModel
import kotlinx.android.synthetic.main.fragment_my_city.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class MyCity : Fragment() {

    private lateinit var viewModel: MyCityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyCityViewModel::class.java)






        btnSearch.setOnClickListener {
            viewModel.refresh(etMyCItyQuery.text.toString())



            viewModel.weather.value?.let { it1 -> bind(it1) }


        }

        btnMarkAsFav.setOnClickListener {
            val cityname = etMyCItyQuery.text.toString()
            if (cityname.isNullOrEmpty()) {

            } else {
                viewModel.insertCity(cityname)
            }
        }
        btnFavCities.setOnClickListener {
            onGotoCompare(it)
        }


    }


    @SuppressLint("SetTextI18n")
    fun bind(weatherData: WeatherData) {
        etResponse.text = """
            City=${weatherData.name} Country=${weatherData.sys.country}
            Latitude=${weatherData.coord.lat} Longitude=${weatherData.coord.lon}            
            Weather Data
            Temparature:${weatherData.main.temp}
            Temparature feels like:${weatherData.main.feelsLike}
            Temparature temp_max:${weatherData.main.tempMax}
            Temparature temp_min:${weatherData.main.tempMin}
            Pressure:${weatherData.main.pressure} Humidity:${weatherData.main.humidity}          
            """.trimIndent()
    }

    private fun onGotoCompare(v: View) {
        val action = MyCityDirections.goToCompareCity()
        Navigation.findNavController(v).navigate(action)
    }
}