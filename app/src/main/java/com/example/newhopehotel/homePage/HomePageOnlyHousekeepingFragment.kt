package com.example.newhopehotel.homePage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.R
import com.example.newhopehotel.checkInCheckOut.CheckInCheckOut
import com.example.newhopehotel.customerFeedback.CustomerFeedback1
import com.example.newhopehotel.databinding.FragmentHomePageBinding
import com.example.newhopehotel.databinding.FragmentHomepageOnlyHousekeepingBinding
import com.example.newhopehotel.housekeeping.Housekeeping
import com.example.newhopehotel.roomService.RoomServiceMain
import com.example.newhopehotel.utils.provideRepository

class HomePageOnlyHousekeepingFragment : Fragment() {

    private lateinit var homepageViewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val binding: FragmentHomepageOnlyHousekeepingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_homepage_only_housekeeping, container, false
        )

        val application = requireNotNull(this.activity).application

        val factory = HomePageViewModelFactory(provideRepository(requireContext()), application)

        homepageViewModel = ViewModelProvider(this, factory).get(HomePageViewModel::class.java)

        binding.myHomePageViewModel = homepageViewModel

        binding.lifecycleOwner = this

        binding.housekeepingButton.setOnClickListener {
            run {
                val intent = Intent(requireActivity(), Housekeeping::class.java)
                startActivity(intent)
            }
        }
        return binding.root
    }


}