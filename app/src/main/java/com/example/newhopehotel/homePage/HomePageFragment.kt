package com.example.newhopehotel.homePage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.R
import com.example.newhopehotel.checkInCheckOut.CheckInCheckOut
import com.example.newhopehotel.customerFeedback.CustomerFeedback1
import com.example.newhopehotel.database.HotelDatabase
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.databinding.FragmentHomePageBinding
import com.example.newhopehotel.housekeeping.Housekeeping
import com.example.newhopehotel.roomService.RoomService

class HomePageFragment : Fragment() {

    private lateinit var homepageViewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomePageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_page, container, false
        )

        val application = requireNotNull(this.activity).application

        val registerDao = HotelDatabase.getInstance(application).registerDatabaseDao
        val checkInCheckoutDao = HotelDatabase.getInstance(application).checkInCheckOutDatabaseDao

        val repository = HotelRepository(registerDao, checkInCheckoutDao)

        val factory = HomePageViewModelFactory(repository, application)

        homepageViewModel = ViewModelProvider(this, factory).get(HomePageViewModel::class.java)

        binding.myHomePageViewModel = homepageViewModel

        binding.lifecycleOwner = this

        binding.cicoButton.setOnClickListener {
            run {
                val intent = Intent(requireActivity(), CheckInCheckOut::class.java)
                startActivity(intent)
            }
        }

        binding.housekeepingButton.setOnClickListener {
            run {
                val intent = Intent(requireActivity(), Housekeeping::class.java)
                startActivity(intent)
            }
        }

        binding.roomServiceButton.setOnClickListener {
            run {
                val intent = Intent(requireActivity(), RoomService::class.java)
                startActivity(intent)
            }
        }

        binding.custFeedbackButton.setOnClickListener {
            run {
                val intent = Intent(requireActivity(), CustomerFeedback1::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }


}