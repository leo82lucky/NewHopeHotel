package com.example.newhopehotel.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newhopehotel.R
import com.example.newhopehotel.database.HotelDatabase
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val application = requireNotNull(this.activity).application

        val registerDao = HotelDatabase.getInstance(application).registerDatabaseDao
        val checkInCheckoutDao = HotelDatabase.getInstance(application).checkInCheckOutDatabaseDao

        val repository = HotelRepository(registerDao, checkInCheckoutDao)

        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this


        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                displayUsersList()
                loginViewModel.doneNavigatingRegister()
            }
        })

        loginViewModel.errotoast.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.errotoastUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(
                    requireContext(),
                    "User doesnt exist,please Register!",
                    Toast.LENGTH_SHORT
                ).show()
                loginViewModel.donetoastErrorUsername()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoastInvalidPassword()
            }
        })

        loginViewModel.navigatetoUserDetails.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "navigate user details")
                Toast.makeText(requireContext(), "To The User Details", Toast.LENGTH_SHORT).show()
                navigateUserDetails()
                loginViewModel.doneNavigatingUserDetails()
            }
        })

        loginViewModel.navigateToHomePage.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "navigate to home page")
                Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show()
                navigateHomePage()
                loginViewModel.doneNavigatingHomePage()
            }
        })

        return binding.root
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateUserDetails() {
        Log.i("MYTAG", "insidisplayUsersDetails")
        val action = LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun navigateHomePage() {
        Log.i("MYTAG", "insidisplayHomePage")
        val action = LoginFragmentDirections.actionLoginFragmentToHomePageFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}



