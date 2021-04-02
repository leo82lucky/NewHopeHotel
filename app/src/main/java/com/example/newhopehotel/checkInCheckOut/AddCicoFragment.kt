package com.example.newhopehotel.checkInCheckOut

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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

        //If there is no id specified in the arguments, then it should be a new toy
        val chosenRoom: CheckInCheckOutEntity? = arguments?.getParcelable(CHOSEN_CICO)

        //Get the view model instance and pass it to the binding implementation
        val factory = AddCicoViewModelFactory(provideRepository(requireContext()), chosenRoom)
        viewModel = ViewModelProvider(this, factory).get(AddCicoViewModel::class.java)

        binding.myAddCicoViewModel = viewModel

        binding.selectDateButton.setOnClickListener { pickDate() }
        binding.selectTimeButton.setOnClickListener { pickTime() }
        binding.fab.setOnClickListener {
            saveToy()
        }
    }

    private fun saveToy() {
        // Check if toy name is not empty
        if (viewModel.cicoBeingModified.custName.isBlank()) {
            context?.toast(R.string.cico_empty_warning)
            return
        }
        viewModel.saveCico()
        fragmentManager?.popBackStack()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //If up button is clicked, check for changes before popping the fragment off
        if (item.itemId == android.R.id.home) {
            onBackClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    /*This can be triggered either by up or both buttons. In both cases,
    we first need to check whether there are unsaved changes and warn the user if necessary*/
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
            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                // Continue with back operation
                fragmentManager?.popBackStack()
            }
            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun pickDate() {
        val currentDate = Calendar.getInstance()
        val startYear = currentDate.get(Calendar.YEAR)
        val startMonth = currentDate.get(Calendar.MONTH)
        val startDay = currentDate.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, year, month, day ->
            val pickedDate = Calendar.getInstance()
            pickedDate.set(year, month, day)
            setDateTextView(pickedDate)
        }, startYear, startMonth, startDay).show()
    }

    private fun pickTime() {
        val currentTime = Calendar.getInstance()
        val startHour = currentTime.get(Calendar.HOUR)
        val startMinute = currentTime.get(Calendar.MINUTE)
        val style = R.style.SpinnerTimePickerDialog

        TimePickerDialog(
            requireContext(), style,
            { _, hour, minute ->
                val pickedTime = Calendar.getInstance()
                pickedTime.set(hour, minute)
                setTimeTextView(pickedTime)
            }, startHour, startMinute, false
        ).show()
    }

    private fun setDateTextView(pickedDate: Calendar) {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate: String = dateFormat.format(pickedDate.time)
        (formattedDate).also { binding.dateTextView.text = it }
//        ("${pickedDate.get(Calendar.DAY_OF_MONTH)}/" +
//                "${pickedDate.get(Calendar.MONTH)}/" +
//                "${pickedDate.get(Calendar.YEAR)}").also { binding.dateTextView.text = it }
    }

    private fun setTimeTextView(pickedTime: Calendar) {

        val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
        val formattedTime: String = dateFormat.format(pickedTime.time)
        (formattedTime).also { binding.timeTextView.text = it }
//        ("${pickedTime.get(Calendar.HOUR_OF_DAY)}:" +
//                "${pickedTime.get(Calendar.MINUTE)}").also { binding.timeTextView.text = it }
    }
}