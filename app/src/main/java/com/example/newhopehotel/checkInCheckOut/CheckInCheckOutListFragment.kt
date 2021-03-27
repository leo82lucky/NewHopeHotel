package com.example.newhopehotel.checkInCheckOut

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.database.HotelDatabase
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.databinding.FragmentCheckInCheckOutListBinding
import com.google.android.material.snackbar.Snackbar


class CheckInCheckOutListFragment : Fragment() {
    private lateinit var checkInCheckOutListViewModel: CheckInCheckOutListViewModel
    private lateinit var binding: FragmentCheckInCheckOutListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_check_in_check_out_list, container, false
        )
        val application = requireNotNull(this.activity).application

        val registerDao = HotelDatabase.getInstance(application).registerDatabaseDao
        val checkInCheckoutDao = HotelDatabase.getInstance(application).checkInCheckOutDatabaseDao

        val repository = HotelRepository(registerDao, checkInCheckoutDao)

        val factory = CheckInCheckOutListViewModelFactory(repository, application)

        checkInCheckOutListViewModel =
            ViewModelProvider(this, factory).get(CheckInCheckOutListViewModel::class.java)

        binding.myCheckInCheckOutListViewModel = checkInCheckOutListViewModel

        val adapter = CICORecycleViewAdapter()
        binding.customersRecyclerView.adapter = adapter

        checkInCheckOutListViewModel.customers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        checkInCheckOutListViewModel.navigatetoCheckIn.observe(
            viewLifecycleOwner,
            { hasFinished ->
                if (hasFinished == true) {
                    navigateCheckIn()
                    checkInCheckOutListViewModel.doneNavigatingCheckIn()
                }
            })

        checkInCheckOutListViewModel.navigatetoCheckOut.observe(
            viewLifecycleOwner,
            { hasFinished ->
                if (hasFinished == true) {
                    navigateCheckOut()
                    checkInCheckOutListViewModel.doneNavigatingCheckOut()
                }
            })

//        initRecyclerView()

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

//    private fun initRecyclerView() {
//        binding.customersRecyclerView.layoutManager = LinearLayoutManager(this.context)
//        displayCustomersList()
//    }
//
//    private fun displayCustomersList() {
//        Log.i("MYTAG", "Inside ...CICO_LIST..Fragment")
//        checkInCheckOutListViewModel.customers.observe(viewLifecycleOwner, {
//            binding.customersRecyclerView.adapter = CICORecycleViewAdapter(it)
//        })
//
//    }
}


