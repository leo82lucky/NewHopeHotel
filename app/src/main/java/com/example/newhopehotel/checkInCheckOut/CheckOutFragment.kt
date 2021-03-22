package com.example.newhopehotel.checkInCheckOut

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newhopehotel.R
import com.example.newhopehotel.database.HotelDatabase
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.databinding.FragmentCheckOutBinding


class CheckOutFragment : Fragment() {
    private lateinit var checkOutViewModel: CheckOutViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCheckOutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_check_out, container, false
        )

        val application = requireNotNull(this.activity).application

        val registerDao = HotelDatabase.getInstance(application).registerDatabaseDao
        val checkInCheckoutDao = HotelDatabase.getInstance(application).checkInCheckOutDatabaseDao

        val repository = HotelRepository(registerDao, checkInCheckoutDao)

        val factory = CheckOutViewModelFactory(repository, application)

        checkOutViewModel = ViewModelProvider(this, factory).get(CheckOutViewModel::class.java)

        binding.myCheckOutViewModel = checkOutViewModel

        binding.lifecycleOwner = this

        checkOutViewModel.navigatetoCICOList.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateCICOList()
                checkOutViewModel.doneNavigatingCICOList()
            }
        })

        return binding.root
    }

    private fun navigateCICOList() {
        Log.i("MYTAG", "insidisplayCheckOut")
        val action =
            CheckOutFragmentDirections.actionCheckOutFragmentToCheckInCheckOutList()
        NavHostFragment.findNavController(this).navigate(action)
    }
}