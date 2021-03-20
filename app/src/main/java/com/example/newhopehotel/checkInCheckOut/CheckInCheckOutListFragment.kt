package com.example.newhopehotel.checkInCheckOut

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newhopehotel.R
import com.example.newhopehotel.database.RegisterDatabase
import com.example.newhopehotel.database.RegisterRepository
import com.example.newhopehotel.databinding.FragmentCheckInCheckOutListBinding
import com.example.newhopehotel.login.LoginFragmentDirections
import com.example.newhopehotel.login.LoginViewModel
import com.example.newhopehotel.login.LoginViewModelFactory


class CheckInCheckOutListFragment : Fragment() {
    private lateinit var checkInCheckOutListViewModel: CheckInCheckOutListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCheckInCheckOutListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_check_in_check_out_list, container, false
        )
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = CheckInCheckOutListViewModelFactory(repository, application)

        checkInCheckOutListViewModel =
            ViewModelProvider(this, factory).get(CheckInCheckOutListViewModel::class.java)

        binding.myCheckInCheckOutListViewModel = checkInCheckOutListViewModel

        binding.lifecycleOwner = this


        checkInCheckOutListViewModel.navigatetoCheckIn.observe(
            viewLifecycleOwner,
            Observer { hasFinished ->
                if (hasFinished == true) {
                    navigateCheckIn()
                    checkInCheckOutListViewModel.doneNavigatingCheckIn()
                }
            })

        checkInCheckOutListViewModel.navigatetoCheckOut.observe(
            viewLifecycleOwner,
            Observer { hasFinished ->
                if (hasFinished == true) {
                    navigateCheckOut()
                    checkInCheckOutListViewModel.doneNavigatingCheckOut()
                }
            })


        return binding.root
    }

    private fun navigateCheckIn() {
        Log.i("MYTAG", "insidisplayCheckIn")
        val action =
            CheckInCheckOutListFragmentDirections.actionCheckInCheckOutListToCheckInFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun navigateCheckOut() {
        Log.i("MYTAG", "insidisplayCheckOut")
        val action =
            CheckInCheckOutListFragmentDirections.actionCheckInCheckOutListToCheckOutFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}


