package com.example.newhopehotel.userDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.database.RegisterDatabase
import com.example.newhopehotel.database.RegisterRepository
import com.example.newhopehotel.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var binding: FragmentUserDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_details, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = UserDetailsFactory(repository, application)

        userDetailsViewModel =
            ViewModelProvider(this, factory).get(UserDetailsViewModel::class.java)

        binding.userDetailsLayout = userDetailsViewModel

        binding.lifecycleOwner = this

        userDetailsViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                val action =
                    UserDetailsFragmentDirections.actionUserDetailsFragmentToLoginFragment()
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
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        userDetailsViewModel.users.observe(viewLifecycleOwner, Observer {
            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        })

    }

}