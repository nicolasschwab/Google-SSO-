package com.example.testlogin.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class ProfileViewModelFactory(private val account: GoogleSignInAccount) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(account) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}