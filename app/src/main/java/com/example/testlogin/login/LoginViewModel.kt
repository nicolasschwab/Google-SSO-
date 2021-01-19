package com.example.testlogin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _loginClicked = MutableLiveData<Boolean>()
    val loginClicked: LiveData<Boolean>
        get() = _loginClicked


    fun loginClicked(){
        _loginClicked.value = true
    }
}