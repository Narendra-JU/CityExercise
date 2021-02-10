package com.example.cityexercise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cityexercise.model.Login.*
import kotlinx.coroutines.*

class SignUpViewModel(application: Application):AndroidViewModel(application) {
    private val coroutineScope= CoroutineScope(Dispatchers.IO)
    private val db by lazy {
        UserDatabase(getApplication()).userDao()
    }

    val signupComplete=MutableLiveData<Boolean>()
    val error=MutableLiveData<String>()

    fun signup(username:String,password:String){
        coroutineScope.launch {
            val user=db.getUser(username)
            if (user!=null){
                withContext(Dispatchers.Main){
                    error.value="User already exists"
                }
            }else{
                val user= User(username,password.hashCode())
                val userId=db.insertUser(user)
                user.id=userId
                LoginState.login(user)
                withContext(Dispatchers.Main){
                    signupComplete.value=true
                }

            }
        }
    }

}