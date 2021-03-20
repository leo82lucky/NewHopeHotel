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
import com.example.newhopehotel.databinding.FragmentCheckOutBinding
import com.example.newhopehotel.login.LoginViewModelFactory


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

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = CheckOutViewModelFactory(repository, application)

        checkOutViewModel = ViewModelProvider(this, factory).get(CheckOutViewModel::class.java)

        binding.myCheckOutViewModel = checkOutViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}