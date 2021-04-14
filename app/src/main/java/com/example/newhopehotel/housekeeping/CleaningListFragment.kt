package com.example.newhopehotel.housekeeping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CleaningListEntity
import com.example.newhopehotel.databinding.FragmentCleaningListBinding
import kotlinx.android.synthetic.main.fragment_cleaning_list.*

const val CHOSEN_CLEANING_LIST = "chosenCleaningList"

class CleaningListFragment : Fragment(), CleaningListAdapter.CleaningListClickListener {

    private lateinit var cleaningListViewModel: CleaningListViewModel

    private lateinit var mAdapter: CleaningListAdapter
    private lateinit var binding: FragmentCleaningListBinding
    private var mCleaningList: List<CleaningListEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cleaning_list, container, false)

        cleaningListViewModel = ViewModelProvider(this).get(CleaningListViewModel::class.java)

        mAdapter = CleaningListAdapter(this)

        binding.rvCleaningList.adapter = mAdapter
        binding.rvCleaningList.layoutManager = LinearLayoutManager(this.context)

        binding.btnAssignWork.setOnClickListener { /*openWorkerFrag(WorkerFragment())*/
            Toast.makeText(this.context, "::" + mAdapter.itemCount, Toast.LENGTH_SHORT).show()
        }

        binding.btnMarkAsDone.setOnClickListener {
            //check which is marked
            //update their room status
            //delete them from list/database

            //testing by adding in data
            cleaningListViewModel.insertCleaningList(CleaningListEntity(1, RoomID.R001, "8am"))
            cleaningListViewModel.insertCleaningList(CleaningListEntity(2, RoomID.R002, "12pm"))
            cleaningListViewModel.insertCleaningList(CleaningListEntity(3, RoomID.R003, "4pm"))
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = cleaningListViewModel.uiState

        var temp: LiveData<List<CleaningListEntity>>? = cleaningListViewModel.cleaningList
        temp?.observe(viewLifecycleOwner, { temp2 ->
            if (temp2.isNullOrEmpty()) {
                cleaningListViewModel.uiState.set(UIState.EMPTY)
            } else {
                cleaningListViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.cleaningList = temp2
                mCleaningList = temp2
            }
        })

        //arrangeCleaningListByTest(cleaningListViewModel.cleaningList)
    }

    override fun onCleaningListClicked(chosenToy: CleaningListEntity) {

    }

    private fun openWorkerFrag(frag: WorkerFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }

    private fun arrangeCleaningListByTest(test: LiveData<List<CleaningListEntity>>?) {
        test?.observe(viewLifecycleOwner, { cleaningListByTest ->
            if (cleaningListByTest.isNullOrEmpty()) {
                cleaningListViewModel.uiState.set(UIState.EMPTY)
            } else {
                cleaningListViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.cleaningList = cleaningListByTest
                mCleaningList = cleaningListByTest
            }
        })
    }
}
























