package com.example.newhopehotel.checkInCheckOut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.R
import com.example.newhopehotel.database.RegisterDatabase
import com.example.newhopehotel.database.RegisterRepository
import com.example.newhopehotel.databinding.FragmentCheckInBinding
import com.example.newhopehotel.login.LoginViewModelFactory

class CheckInFragment : Fragment() {

    private lateinit var checkInViewModel: CheckInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCheckInBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_check_in, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = CheckInViewModelFactory(repository, application)

        checkInViewModel = ViewModelProvider(this, factory).get(CheckInViewModel::class.java)

        binding.myCheckInViewModel = checkInViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
