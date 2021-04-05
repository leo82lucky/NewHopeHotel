package com.example.newhopehotel.roomService.viewRoomServiceList

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.R
import com.example.newhopehotel.database.MorningCallEntity
import com.example.newhopehotel.database.RoomServiceEntity
import com.example.newhopehotel.databinding.AddMorningCallBinding
import com.example.newhopehotel.databinding.AddRoomServiceBinding
import com.example.newhopehotel.utils.provideRepository
import org.jetbrains.anko.toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddRoomServiceFragment : Fragment() {

    private lateinit var binding: AddRoomServiceBinding
    private lateinit var viewModel: AddRoomServiceViewModel

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.add_room_service_fragment, container, false
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //If there is no id specified in the arguments, then it should be a new toy
        val chosenRoom: RoomServiceEntity? = arguments?.getParcelable(CHOSEN_RS)

        //Get the view model instance and pass it to the binding implementation
        val factory = AddRoomServiceViewModelFactory(provideRepository(requireContext()), chosenRoom)
        viewModel = ViewModelProvider(this, factory).get(AddRoomServiceViewModel::class.java)

        binding.myAddRoomServiceViewModel= viewModel
        binding.addNewButton.setOnClickListener { saveToy() }
    }

    private fun saveToy() {
        // Check if toy name is not empty
        if (viewModel.roomServiceBeingModified.custName.isBlank()) {
            context?.toast(R.string.cico_empty_warning)
            return
        }
        viewModel.saveRoomService()
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

    private fun setDateTextView(pickedDate: Calendar, textViewDate: TextView) {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate: String = dateFormat.format(pickedDate.time)
        (formattedDate).also {
            textViewDate.text = it
        }
//        ("${pickedDate.get(Calendar.DAY_OF_MONTH)}/" +
//                "${pickedDate.get(Calendar.MONTH)}/" +
//                "${pickedDate.get(Calendar.YEAR)}").also { binding.dateTextView.text = it }
    }

    private fun setTimeTextView(pickedTime: Calendar, textViewTime: TextView) {
        val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
        val formattedTime: String = dateFormat.format(pickedTime.time)
        (formattedTime).also {
            textViewTime.text = it
        }
//        ("${pickedTime.get(Calendar.HOUR_OF_DAY)}:" +
//                "${pickedTime.get(Calendar.MINUTE)}").also { binding.timeTextView.text = it }
    }
}