package com.example.newhopehotel.housekeeping

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.newhopehotel.R
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.CleaningListEntity
import com.example.newhopehotel.database.RoomToCleanEntity
import com.example.newhopehotel.databinding.FragmentCleaningListBinding
import kotlinx.android.synthetic.main.fragment_cleaning_list.*
import org.jetbrains.anko.design.longSnackbar

const val CHOSEN_CLEANING_LIST = "chosenCleaningList"

class CleaningListFragment : Fragment(), CleaningListAdapter.CleaningListClickListener {

    private lateinit var cleaningListViewModel: CleaningListViewModel

    private lateinit var mAdapter: CleaningListAdapter
    private lateinit var binding: FragmentCleaningListBinding
    private var mCleaningList: List<CleaningListEntity>? = null
    private var mRoomsToClean: List<CheckInCheckOutEntity>? = null

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

        binding.btnAssignWork.setOnClickListener {
            openWorkerFrag(WorkerFragment())
        }


        val coordinator: FrameLayout? = activity?.findViewById(R.id.main_container)
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val cleaningListToErase = mCleaningList!![position]

                cleaningListViewModel.deleteCleaningList(cleaningListToErase)

                coordinator?.longSnackbar("One task completed", "UNDO") {
                    cleaningListViewModel.insertCleaningList(cleaningListToErase)
                }
            }
        }).attachToRecyclerView(binding.rvCleaningList)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = cleaningListViewModel.uiState

        var temp: LiveData<List<CleaningListEntity>>? = cleaningListViewModel.cleaningListOfUserID
        temp?.observe(viewLifecycleOwner, { temp2 ->
            if (temp2.isNullOrEmpty()) {
                cleaningListViewModel.uiState.set(UIState.EMPTY)
            } else {
                cleaningListViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.cleaningList = temp2
                mCleaningList = temp2
            }
        })
    }

    override fun onCleaningListClicked(chosenToy: CleaningListEntity) {

    }

    private fun openWorkerFrag(frag: WorkerFragment) {
        WorkerFragment.selectedWorkerId = -1

        var tempList: LiveData<List<CheckInCheckOutEntity>>? = cleaningListViewModel.cicoStatusAvailable
        tempList?.observe(viewLifecycleOwner, { list ->
            if (list.isNullOrEmpty()){

            } else {
                mRoomsToClean = list
            }

        })

        // test data
        cleaningListViewModel.deleteAllRoomToClean()
        mRoomsToClean?.forEach {
            cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8am", it.roomID))
        }

        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }
}
























