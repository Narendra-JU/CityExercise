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
import com.example.cityexercise.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*




class SignUpFragment : Fragment() {

    private lateinit var viewmodel:SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignUp.setOnClickListener { onSignUp(it) }
        btnLogin.setOnClickListener { onGoToLogin(it) }
        viewmodel=ViewModelProvider(this).get(SignUpViewModel::class.java)

    }

    private fun onSignUp(v: View?) {
        val username=logInUsername.text.toString()
        val password=logInPassword.text.toString()
        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            Toast.makeText(activity, "All fields are not filled.Try again", Toast.LENGTH_SHORT).show()
        }else{
            viewmodel.signup(username,password)
        }
    }

    private fun observeViewModel(){
        viewmodel.signupComplete.observe(viewLifecycleOwner, Observer { isComplete ->
            Toast.makeText(activity, "Signup Complete,Go to Login", Toast.LENGTH_SHORT).show()
            val action=SignUpFragmentDirections.actionGoToLoginPage()
            Navigation.findNavController(logInUsername).navigate(action)
        })

    }



    private fun onGoToLogin(v:View){
        val action=SignUpFragmentDirections.actionGoToLoginPage()
        Navigation.findNavController(v).navigate(action)
    }
}