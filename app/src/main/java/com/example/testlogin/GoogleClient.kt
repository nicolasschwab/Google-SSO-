package com.example.testlogin

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleClient(private val activity: FragmentActivity) {

    private var mGoogleSignInClient: GoogleSignInClient

    init {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    fun signIn(): Intent {
        return  mGoogleSignInClient.signInIntent
    }

    fun loginSuccessful(intent: Intent?): Boolean{
        val task =
            GoogleSignIn.getSignedInAccountFromIntent(intent)

        return try {
            task.getResult(ApiException::class.java)
            true
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Should log error
            false
        }
    }

    fun getLoggedInAccount(): GoogleSignInAccount?{
        return GoogleSignIn.getLastSignedInAccount(activity!!)
    }

    fun signOut(){
        mGoogleSignInClient.signOut()
    }

}