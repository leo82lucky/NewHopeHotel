package com.example.newhopehotel.checkInCheckOut

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newhopehotel.R
import com.example.newhopehotel.data.DatePickerHelper
import com.example.newhopehotel.data.TimePickerHelper
import com.example.newhopehotel.database.RegisterDatabase
import com.example.newhopehotel.database.RegisterRepository
import com.example.newhopehotel.databinding.FragmentCheckInBinding
import kotlinx.android.synthetic.main.fragment_check_in.*
import java.util.*

class CheckInFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var checkInViewModel: CheckInViewModel
    lateinit var timePicker: TimePickerHelper
    lateinit var datePicker: DatePickerHelper

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

        //Spinner for Room ID dropdown Item
        val roomIDSpinner: Spinner = binding.roomIDSpinner
        ArrayAdapter.createFromResource(
            requireContext(), R.array.roomid_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roomIDSpinner.adapter = adapter
        }

        //Spinner for Room Type dropdown Item
        val roomTypeSpinner: Spinner = binding.roomTypeSpinner
        ArrayAdapter.createFromResource(
            requireContext(), R.array.roomtype_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roomTypeSpinner.adapter = adapter
        }

        //Spinner for Room Type dropdown Item
        val roomStatusSpinner: Spinner = binding.roomStatusSpinner
        ArrayAdapter.createFromResource(
            requireContext(), R.array.roomstatus_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roomStatusSpinner.adapter = adapter
        }

        //Spinner for Room Type dropdown Item
        val roomCardNoSpinner: Spinner = binding.roomCardNoSpinner
        ArrayAdapter.createFromResource(
            requireContext(), R.array.cardno_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roomCardNoSpinner.adapter = adapter
        }

        timePicker = TimePickerHelper(requireContext(), is24HourView = false, isSpinnerType = true)
        binding.selectTimeButton.setOnClickListener {
            showTimePickerDialog()
        }

        datePicker = DatePickerHelper(requireContext())
        binding.selectDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        checkInViewModel.navigatetoCICOList.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateCICOList()
                checkInViewModel.doneNavigatingCICOList()
            }
        })

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val item = parent.getItemAtPosition(pos).toString()
        Toast.makeText(requireContext(), "Selected$item", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        Toast.makeText(requireContext(), "Nothing Selected", Toast.LENGTH_SHORT).show()
    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        timePicker.showDialog(h, m, object : TimePickerHelper.Callback {
            override fun onTimeSelected(hourOfDay: Int, minute: Int) {
                val hourStr = if (hourOfDay < 10) "0${hourOfDay}" else "$hourOfDay"
                val minuteStr = if (minute < 10) "0${minute}" else "$minute"
                timeTextView.text = "${hourStr}:${minuteStr}"
            }
        })
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        datePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "$dayofMonth"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "$mon"
                dateTextView.text = "${dayStr}/${monthStr}/${year}"
            }
        })
    }

    private fun navigateCICOList() {
        Log.i("MYTAG", "insidisplayCheckOut")
        val action =
            CheckInFragmentDirections.actionCheckInFragmentToCheckInCheckOutList()
        NavHostFragment.findNavController(this).navigate(action)
    }
}

