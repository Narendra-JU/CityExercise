package com.example.cityexercise.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.cityexercise.R
import com.example.cityexercise.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel:LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLoginToCity.setOnClickListener {
            onLogin(it)
        }

        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        observeViewModel()

    }

    private fun observeViewModel(){
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer { isComplete ->
            Toast.makeText(activity, "Login Completed", Toast.LENGTH_SHORT).show()
            val action=LoginFragmentDirections.actionGoToMyCity()
            Navigation.findNavController(logInPasswordLogin).navigate(action)

        })
    }

    private fun onLogin(v:View){
        val username=logInUsernameLogin.text.toString()
        val password=logInPasswordLogin.text.toString()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()){
            Toast.makeText(activity, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.login(username,password)
        }
    }



}