
package com.example.newhopehotel.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.FragmentLoginBinding
import com.example.newhopehotel.utils.provideRepository


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val application = requireNotNull(this.activity).application

        val factory = LoginViewModelFactory(provideRepository(requireContext()), application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this


        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateRegister()
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

        loginViewModel.navigateToHomePage.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show()
                navigateHomePage()
                loginViewModel.doneNavigatingHomePage()
            }
        })

        loginViewModel.navigatetoHomePageOnlyHousekeeping.observe(
            viewLifecycleOwner,
            { hasFinished ->
                if (hasFinished == true) {
                    Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT)
                        .show()
                    navigateHomePageOnlyHousekeeping()
                    loginViewModel.doneNavigatingHomePageOnlyHousekeeping()
                }
            })

        return binding.root
    }


    private fun navigateRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateHomePage() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomePageFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun navigateHomePageOnlyHousekeeping() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomePageOnlyHousekeepingFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}




