package com.example.newhopehotel.homePage

import android.content.Intent
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
import com.example.newhopehotel.checkInCheckOut.CheckInCheckOut
import com.example.newhopehotel.customerFeedback.CustomerFeedback1
import com.example.newhopehotel.databinding.FragmentHomePageBinding
import com.example.newhopehotel.housekeeping.Housekeeping
import com.example.newhopehotel.login.LoginFragmentDirections
import com.example.newhopehotel.roomService.RoomServiceMain
import com.example.newhopehotel.utils.provideRepository

class HomePageFragment : Fragment() {

    private lateinit var homepageViewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val binding: FragmentHomePageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_page, container, false
        )

        val application = requireNotNull(this.activity).application

        val factory = HomePageViewModelFactory(provideRepository(requireContext()), application)

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
                val intent = Intent(requireActivity(), RoomServiceMain::class.java)
                startActivity(intent)
            }
        }

        binding.custFeedbackButton.setOnClickListener {
            run {
                val intent = Intent(requireActivity(), CustomerFeedback1::class.java)
                startActivity(intent)
            }

        }

        homepageViewModel.navigatetoUserDetails.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "navigate user details")
                Toast.makeText(requireContext(), "To The User Details", Toast.LENGTH_SHORT).show()
                navigateUserDetails()
                homepageViewModel.doneNavigatingUserDetails()
            }
        })

        return binding.root
    }


    private fun navigateUserDetails() {
        val action = HomePageFragmentDirections.actionHomePageFragmentToUserDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}