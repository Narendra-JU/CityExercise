package com.example.cityexercise.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cityexercise.model.Login.LoginState
import com.example.cityexercise.model.Login.UserDatabase
import kotlinx.coroutines.*

class LoginViewModel(application: Application):AndroidViewModel(application) {
    private val coroutineScope= CoroutineScope(Dispatchers.IO)
    private val db by lazy {
        UserDatabase(getApplication()).userDao()
    }
    val loginComplete=MutableLiveData<Boolean>()
    val error= MutableLiveData<String>()

    fun login(username:String,password:String){
        coroutineScope.launch {
            val user=db.getUser(username)
            if(user==null){
                withContext(Dispatchers.Main){
                    error.value="User not found"
                    Log.d("CityUserRoom", "login:${error.value}")
                }
            }else{
                if (user.passwordHash==password.hashCode()){
                    LoginState.login(user)
                    withContext(Dispatchers.Main){
                        loginComplete.value=true
                    }
                }else{
                    withContext(Dispatchers.Main){
                        error.value="Password is incorrect"
                        Log.d("TAG", "login: Password is incorrect")
                    }
                }
            }
        }
    }

}