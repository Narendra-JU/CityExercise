package com.example.cityexercise.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cityexercise.R
import com.example.cityexercise.viewmodel.MyCityViewModel
import kotlinx.android.synthetic.main.fragment_compare_city.*


class CompareCity : Fragment() {

    private lateinit var viewModel: MyCityViewModel
    lateinit var str: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compare_city, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyCityViewModel::class.java)
        viewModel.getCity()
        str = ""
        btnshow.setOnClickListener {
            Log.d("favliststr", "onViewCreated: ${viewModel.list.value}")
            viewModel.list.value?.forEach {
                str = str + it.favcity + "\n"
            }
            citylist.text = str


        }


    }
}