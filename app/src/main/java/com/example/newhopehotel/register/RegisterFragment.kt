package com.example.newhopehotel.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.FragmentRegisterBinding
import com.example.newhopehotel.utils.provideRepository
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )

        val application = requireNotNull(this.activity).application

        val factory = RegisterViewModelFactory(provideRepository(requireContext()), application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateto.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateLogin()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner, {
        })


        registerViewModel.errotoast.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errotoastUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Username Already taken", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastUserName()
            }
        })

        registerViewModel.errotoastFormatUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), R.string.usernameError, Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastFormatUserName()
                binding.userNameTextView.error = getString(R.string.usernameError)
            }
        })
        registerViewModel.errotoastFormatPassword.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), R.string.passwordFormatError, Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastFormatPassword()
                binding.passwordTextView.error = getString(R.string.passwordFormatError)
            }
        })

        return binding.root
    }

    private fun navigateLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

}