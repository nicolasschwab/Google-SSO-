package com.example.testlogin.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testlogin.GoogleClient
import com.example.testlogin.R
import com.example.testlogin.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment() {

    private lateinit var googleClient: GoogleClient
    private lateinit var viewModel: ProfileViewModel
    private lateinit var viewModelFactory: ProfileViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<ProfileFragmentBinding>(
            inflater,
            R.layout.profile_fragment,
            container,
            false
        )

        googleClient = GoogleClient(this.activity!!)

        viewModelFactory = ProfileViewModelFactory(googleClient.getLoggedInAccount()!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.photoUrl.observe(viewLifecycleOwner, Observer { uri ->
            Glide.with(this.activity).load(uri).into(binding.imageView2)
            println(uri.toString())
            binding.imageView2.setImageURI(uri)
        })

        return binding.root

    }
}