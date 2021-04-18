package com.example.newhopehotel.checkInCheckOut

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.R
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.AddCicoBinding
import com.example.newhopehotel.utils.provideRepository
import org.jetbrains.anko.toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class AddCicoFragment : Fragment() {

    private lateinit var binding: AddCicoBinding
    private lateinit var viewModel: AddCicoViewModel

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.add_cico_fragment, container, false
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val chosenRoom: CheckInCheckOutEntity? = arguments?.getParcelable(CHOSEN_CICO)

        val factory = AddCicoViewModelFactory(provideRepository(requireContext()), chosenRoom)
        viewModel = ViewModelProvider(this, factory).get(AddCicoViewModel::class.java)

        
        binding.myAddCicoViewModel = viewModel

        binding.selectDateButton.setOnClickListener { pickDate(binding.dateTextView) }
        binding.selectTimeButton.setOnClickListener { pickTime(binding.timeTextView) }
        binding.selectCheckoutDateButton.setOnClickListener { pickDate(binding.checkoutDateTextView) }
        binding.selectCheckoutTimeButton.setOnClickListener { pickTime(binding.checkoutTimeTextView) }
        binding.saveButton.setOnClickListener { saveCico() }
    }

    private fun saveCico() {
        if (viewModel.cicoBeingModified.custName.isBlank()) {
            context?.toast(R.string.cico_empty_warning)
            return
        }
        if (!viewModel.cicoBeingModified.contactNo.isPhoneNumber()) {
            binding.contactNoLayout.error = getString(R.string.contactNoError)
            context?.toast(R.string.contact_toast_error)
            return
        } else {
            binding.contactNoLayout.error = null
        }

        if (!viewModel.cicoBeingModified.icNo.isICNumber()) {
            binding.icLayout.error = getString(R.string.icError)
            context?.toast(R.string.ic_toast_error)
            return
        } else {
            binding.icLayout.error = null
        }
        if (viewModel.cicoBeingModified.roomStatus == null) {
            Toast.makeText(requireContext(), "Please Select a Room Status", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (viewModel.cicoBeingModified.checkinDate == ("dd/mm/yyyy")) {
            Toast.makeText(requireContext(), "Please Select a Check in Date", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (viewModel.cicoBeingModified.checkinTime == ("hh:mm am")) {
            Toast.makeText(requireContext(), "Please Select a Check In Time", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (viewModel.cicoBeingModified.checkoutDate == ("dd/mm/yyyy")) {
            Toast.makeText(requireContext(), "Please Select a Check Out Date", Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (viewModel.cicoBeingModified.checkoutTime == "hh:mm am") {
            Toast.makeText(requireContext(), "Please Select a Check Out Time", Toast.LENGTH_SHORT)
                .show()
            return
        }
        viewModel.saveCico()
        fragmentManager?.popBackStack()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackClicked()
        }
        return super.onOptionsItemSelected(item)
    }


    fun onBackClicked() {
        if (viewModel.isChanged) {
            openAlertDialog()
        } else {
         fragmentManager?.popBackStack()
        }
    }

    private fun openAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.unsaved_changes_warning_title))
            .setMessage(getString(R.string.unsaved_changes_warning_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                // Continue with back operation
                fragmentManager?.popBackStack()
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun pickDate(textViewDate: TextView) {
        val currentDate = Calendar.getInstance()
        val startYear = currentDate.get(Calendar.YEAR)
        val startMonth = currentDate.get(Calendar.MONTH)
        val startDay = currentDate.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, year, month, day ->
            val pickedDate = Calendar.getInstance()
            pickedDate.set(year, month, day)
            setDateTextView(pickedDate, textViewDate)
        }, startYear, startMonth, startDay).show()
    }

    private fun pickTime(textViewTime: TextView) {
        val currentTime = Calendar.getInstance()
        val startHour = currentTime.get(Calendar.HOUR)
        val startMinute = currentTime.get(Calendar.MINUTE)
        val style = R.style.SpinnerTimePickerDialog

        TimePickerDialog(
            requireContext(), style,
            { _, hour, minute ->
                val pickedTime = Calendar.getInstance()
                pickedTime.set(hour, minute)
                setTimeTextView(pickedTime, textViewTime)
            }, startHour, startMinute, false
        ).show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDateTextView(pickedDate: Calendar, textViewDate: TextView) {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate: String = dateFormat.format(pickedDate.time)
        (formattedDate).also {
            textViewDate.text = it
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setTimeTextView(pickedTime: Calendar, textViewTime: TextView) {
        val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
        val formattedTime: String = dateFormat.format(pickedTime.time)
        (formattedTime).also {
            textViewTime.text = it
        }
    }

    private var CONTACT_NO_PATTERN: Pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$")
    private fun CharSequence.isPhoneNumber(): Boolean = CONTACT_NO_PATTERN.matcher(this).find()

    private var IC_PATTERN: Pattern = Pattern.compile("^\\d{6}[- .]?\\d{2}[- .]?\\d{4}$")
    private fun CharSequence.isICNumber(): Boolean = IC_PATTERN.matcher(this).find()
}