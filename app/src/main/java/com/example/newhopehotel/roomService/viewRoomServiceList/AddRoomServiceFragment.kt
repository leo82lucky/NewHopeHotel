package com.example.newhopehotel.roomService.viewRoomServiceList

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.R
import com.example.newhopehotel.checkInCheckOut.CICOViewModel
import com.example.newhopehotel.checkInCheckOut.CicoAdapter
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.MorningCallEntity
import com.example.newhopehotel.database.RoomServiceEntity
import com.example.newhopehotel.databinding.AddRoomServiceBinding
import com.example.newhopehotel.databinding.FragmentCicoListBinding
import com.example.newhopehotel.utils.provideRepository
import kotlinx.android.synthetic.main.activity_morning_call.*
import kotlinx.android.synthetic.main.activity_room_service.*
import kotlinx.android.synthetic.main.add_room_service_fragment.*
import org.jetbrains.anko.toast
import java.util.*

class AddRoomServiceFragment : Fragment() {

    private lateinit var binding: AddRoomServiceBinding
    private lateinit var viewModel: AddRoomServiceViewModel
    private var mCicoList: List<CheckInCheckOutEntity>? = null
    private lateinit var cicoActivityViewModel: CICOViewModel
    val customerNameList= mutableListOf("Please Select a Customer")
    val roomNoList= mutableListOf("")
    var spinCount=0


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
        cicoActivityViewModel = ViewModelProvider(this).get(CICOViewModel::class.java)
        setHasOptionsMenu(true)


        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as RoomService).activateAddRoomServiceTitle()
        tv_name_data!!.visibility = View.GONE
        sp_name!!.visibility = View.VISIBLE
        val chosenRoom: RoomServiceEntity? = arguments?.getParcelable(CHOSEN_RS)

        val factory = AddRoomServiceViewModelFactory(provideRepository(requireContext()), chosenRoom)
        viewModel = ViewModelProvider(this, factory).get(AddRoomServiceViewModel::class.java)

        binding.myAddRoomServiceViewModel= viewModel

        cicoActivityViewModel.cicoList?.observe(viewLifecycleOwner, { cicoEntries ->
            if (cicoEntries.isNullOrEmpty()) {
                cicoActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                cicoActivityViewModel.uiState.set(UIState.HAS_DATA)
                mCicoList = cicoEntries
                setNameArray(cicoEntries)

            }
        })





        var adapter=   ArrayAdapter(requireActivity().baseContext,android.R.layout.simple_list_item_1,customerNameList)
        adapter.notifyDataSetChanged()
        sp_name.adapter=adapter
        sp_name.onItemSelectedListener=object : AdapterView.OnItemSelectedListener
        {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if((activity as RoomService).editMode==true)
                {
                    tv_name_data!!.visibility = View.VISIBLE
                    sp_name!!.visibility = View.GONE
                    (activity as RoomService).editMode=false
                }
                else if(spinCount>0)
                {
                    customerNameCollector.setText(customerNameList[position])
                   tv_room_no_data.setText(roomNoList[position])



                }
                else
                {

                    customerNameCollector.setText("Please Select a Customer")
                    tv_room_no_data.setText("")

                    spinCount=spinCount.inc()
                }

            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }
        btn_add_room_service.setOnClickListener {

            if (viewModel.isChanged) {
                saveToy()
            } else {
                fragmentManager?.popBackStack()
            }

        }

        btn_cancel.setOnClickListener { onBackClicked() }


    }

    override fun onResume() {
        super.onResume()
        spinCount=0
    }
    fun setNameArray(cicoEntries:List<CheckInCheckOutEntity>) {
        for (item in cicoEntries.indices) {


            customerNameList.add(cicoEntries[item].custName)
            roomNoList.add(cicoEntries[item].roomID.toString())
        }
    }
    private fun saveToy() {
        if (viewModel.roomServiceBeingModified.custName.isBlank()||viewModel.roomServiceBeingModified.custName.equals("Please Select a Customer")) {
            context?.toast(R.string.cico_empty_warning)
            return
        }
        if (viewModel.roomServiceBeingModified.request.isBlank()) {
            context?.toast(R.string.error_no_request)
            return
        }
        Toast.makeText(requireActivity().baseContext,"Room Service List Updated", Toast.LENGTH_SHORT).show()
        viewModel.saveRoomService()
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
                fragmentManager?.popBackStack()
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}