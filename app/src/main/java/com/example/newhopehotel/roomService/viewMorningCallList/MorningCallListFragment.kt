package com.example.newhopehotel.roomService.viewMorningCallList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.newhopehotel.R
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.MorningCallEntity
import com.example.newhopehotel.databinding.FragmentMorningCallListBinding
import com.example.newhopehotel.roomService.viewRoomServiceList.AddRoomServiceFragment
import com.example.newhopehotel.roomService.viewRoomServiceList.CHOSEN_RS
import org.jetbrains.anko.design.longSnackbar

const val CHOSEN_MC = "chosenMC"

class MorningCallListFragment : Fragment(), MorningCallAdapter.MorningCallClickListener {

    private lateinit var morningCallActivityViewModel: MorningCallViewModel

    private lateinit var mAdapter: MorningCallAdapter
    private lateinit var binding: FragmentMorningCallListBinding
    private var mMorningCallList: List<CheckInCheckOutEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_morning_call_list, container, false)

        morningCallActivityViewModel = ViewModelProvider(this).get(MorningCallViewModel::class.java)

        mAdapter = MorningCallAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.recycler) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = morningCallActivityViewModel.uiState

        //Claim list of toys from view model

        morningCallActivityViewModel.morningCallList?.observe(viewLifecycleOwner, { morningCallEntries ->
            if (morningCallEntries.isNullOrEmpty()) {
                morningCallActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                morningCallActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.morningCallList = morningCallEntries
                mMorningCallList = morningCallEntries
            }
        })


    }

    override fun onMorningCallClicked(chosenToy: CheckInCheckOutEntity) {

    }


}