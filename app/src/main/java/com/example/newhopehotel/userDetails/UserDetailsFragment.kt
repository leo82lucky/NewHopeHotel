package com.example.newhopehotel.userDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.FragmentUserDetailsBinding
import com.example.newhopehotel.utils.provideRepository

class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var binding: FragmentUserDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_details, container, false
        )

        val application = requireNotNull(this.activity).application

        val factory = UserDetailsFactory(provideRepository(requireContext()), application)

        userDetailsViewModel =
            ViewModelProvider(this, factory).get(UserDetailsViewModel::class.java)

        binding.userDetailsLayout = userDetailsViewModel

        binding.lifecycleOwner = this

        userDetailsViewModel.navigateto.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                val action =
                    UserDetailsFragmentDirections.actionUserDetailsFragmentToHomePageFragment()
                NavHostFragment.findNavController(this).navigate(action)
                userDetailsViewModel.doneNavigating()
            }
        })

        initRecyclerView()

        return binding.root

    }


    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }


    private fun displayUsersList() {
        userDetailsViewModel.users.observe(viewLifecycleOwner, {
            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        })

    }

}