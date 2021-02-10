package com.example.cityexercise.model.Login

object LoginState {
    var isLoggedIn=false
    var user: User?=null
    fun logout(){
        isLoggedIn =false
        user =null
    }

    fun login(user: User){
        isLoggedIn =true
        LoginState.user =user
    }
}