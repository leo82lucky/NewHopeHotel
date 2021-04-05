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

            fragmentManager?.popBackStack()

    }

}