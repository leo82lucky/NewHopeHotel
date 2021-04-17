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

        // test data
        /*cleaningListViewModel.insertCleaningList(CleaningListEntity(1, RoomID.R001, "8am"))
        cleaningListViewModel.insertCleaningList(CleaningListEntity(2, RoomID.R002, "12pm"))
        cleaningListViewModel.insertCleaningList(CleaningListEntity(3, RoomID.R003, "4pm"))
        Toast.makeText(this.context, "Count : " + mAdapter.itemCount, Toast.LENGTH_SHORT).show()*/

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
    }

    override fun onCleaningListClicked(chosenToy: CleaningListEntity) {

    }

    private fun openWorkerFrag(frag: WorkerFragment) {
        WorkerFragment.selectedWorkerId = -1

        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }


        // test data
        cleaningListViewModel.deleteAllRoomToClean()
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8am", RoomID.R001))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8am", RoomID.R002))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8am", RoomID.R003))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8am", RoomID.R004))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"12pm", RoomID.R005))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"12pm", RoomID.R006))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"12pm", RoomID.R007))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"12pm", RoomID.R008))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"4pm", RoomID.R009))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"4pm", RoomID.R010))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"4pm", RoomID.R011))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"4pm", RoomID.R012))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8pm", RoomID.R013))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8pm", RoomID.R014))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8pm", RoomID.R015))
        cleaningListViewModel.insertRoomToClean(RoomToCleanEntity(Color.parseColor("#D4ECB8"),"8pm", RoomID.R016))
    }
}
























