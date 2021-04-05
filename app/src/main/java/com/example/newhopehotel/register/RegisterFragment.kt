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

//        val registerDao = HotelDatabase.getInstance(application).registerDatabaseDao
//        val checkInCheckoutDao = HotelDatabase.getInstance(application).checkInCheckOutDatabaseDao
//
//        val repository = HotelRepository(registerDao, checkInCheckoutDao)

        val factory = RegisterViewModelFactory(provideRepository(requireContext()), application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateto.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner, {
            Log.i("MYTAG", it.toString() + "000000000000000000000000")
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
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastUserName()
            }
        })

        return binding.root
    }

    private fun displayUsersList() {
        Log.i("MYTAG", "insidisplayUsersList")
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

}