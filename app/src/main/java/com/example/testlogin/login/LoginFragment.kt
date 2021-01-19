package com.example.testlogin.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.testlogin.GoogleClient
import com.example.testlogin.R
import com.example.testlogin.databinding.LoginFragmentBinding

class LoginFragment: Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var googleClient: GoogleClient

    private val RC_SIGN_IN = 12222

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        googleClient = GoogleClient(this.activity!!)

        binding.lifecycleOwner= this

        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.loginClicked.observe(viewLifecycleOwner, Observer {
            val signInIntent = googleClient.signIn()
            startActivityForResult(signInIntent, RC_SIGN_IN)
        })

        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val loginSuccessful = googleClient.loginSuccessful(data)
            if (loginSuccessful){
                val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
        }

    }

    override fun onStart() {

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        googleClient.signOut()
        val account = googleClient.getLoggedInAccount()
        if (account != null){
            Toast.makeText(context!!, "account existe", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context!!, "NO account", Toast.LENGTH_LONG).show()
        }
        super.onStart()
    }
}