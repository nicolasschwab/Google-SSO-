package com.example.testlogin.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class ProfileViewModel(account : GoogleSignInAccount): ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _photoUrl = MutableLiveData<Uri?>()
    val photoUrl: LiveData<Uri?>
        get() = _photoUrl

    init {
        _name.value = account.displayName.orEmpty()
        _email.value = account.email.orEmpty()
        _photoUrl.value = account.photoUrl
    }

}