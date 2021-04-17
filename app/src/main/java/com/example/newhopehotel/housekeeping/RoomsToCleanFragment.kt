package com.example.newhopehotel.housekeeping

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.newhopehotel.R
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CleaningListEntity
import com.example.newhopehotel.database.RoomToCleanEntity
import com.example.newhopehotel.database.SelectedWorkerEntity
import com.example.newhopehotel.databinding.FragmentRoomsToCleanBinding

const val CHOSEN_ROOM_TO_CLEAN = "chosenRoomToClean"

class RoomsToCleanFragment : Fragment(), RoomsToCleanAdapter.RoomToCleanClickListener {

    private lateinit var roomsToCleanViewModel: RoomsToCleanViewModel

    private lateinit var mAdapter: RoomsToCleanAdapter
    private lateinit var binding: FragmentRoomsToCleanBinding
    private var mRoomToCleanList: List<RoomToCleanEntity>? = null

    private var roomIdList = mutableListOf<RoomID>()
    private var roomCleaningTimeList = mutableListOf<String>()

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rooms_to_clean, container, false)

        roomsToCleanViewModel = ViewModelProvider(this).get(RoomsToCleanViewModel::class.java)

        mAdapter = RoomsToCleanAdapter(this)

        binding.rvRoomsToClean.adapter = mAdapter
        binding.rvRoomsToClean.layoutManager = GridLayoutManager(this.context, 4, GridLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = roomsToCleanViewModel.uiState

        arrangeRoomToCleanListByTime(roomsToCleanViewModel.roomToClean8am)

        //selectedWorker = roomsToCleanViewModel.selectedWorkerList[0]
        //Toast.makeText(this.context, "size : " + roomsToCleanViewModel.selectedWorkerList.size, Toast.LENGTH_SHORT).show()

        binding.btn8am.setOnClickListener {
            arrangeRoomToCleanListByTime(roomsToCleanViewModel.roomToClean8am)
        }

        binding.btn12pm.setOnClickListener {
            arrangeRoomToCleanListByTime(roomsToCleanViewModel.roomToClean12pm)
        }

        binding.btn4pm.setOnClickListener {
            arrangeRoomToCleanListByTime(roomsToCleanViewModel.roomToClean4pm)
        }

        binding.btn8pm.setOnClickListener {
            arrangeRoomToCleanListByTime(roomsToCleanViewModel.roomToClean8pm)
        }

        binding.btnAssign.setOnClickListener {
            roomIdList.zip(roomCleaningTimeList).forEach { pair ->
                roomsToCleanViewModel.insertCleaningList(CleaningListEntity(WorkerFragment.selectedWorkerId, pair.component1(), pair.component2()))
            }

            openWorkerFrag(WorkerFragment())
        }
    }

    override fun onRoomToCleanClicked(chosenToy: RoomToCleanEntity) {
        if (chosenToy.borderColor == Color.parseColor("#D4ECB8")) {
            roomsToCleanViewModel.changeRoomToCleanBorderColor(chosenToy.roomID, Color.parseColor("#52A651"))
            roomIdList.add(chosenToy.roomID)
            roomCleaningTimeList.add(chosenToy.cleaningTime)
        }
        else {
            roomsToCleanViewModel.changeRoomToCleanBorderColor(chosenToy.roomID, Color.parseColor("#D4ECB8"))
            var temp = roomIdList.indexOf(chosenToy.roomID)
            roomIdList.removeAt(temp)
            roomCleaningTimeList.removeAt(temp)
        }
    }

    private fun openWorkerFrag(frag: WorkerFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
        }
    }

    private fun arrangeRoomToCleanListByTime(time: LiveData<List<RoomToCleanEntity>>?) {
        time?.observe(viewLifecycleOwner, { listByTime ->
            if (listByTime.isNullOrEmpty()) {
                roomsToCleanViewModel.uiState.set(UIState.EMPTY)
            } else {
                roomsToCleanViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.roomToCleanList = listByTime
                mRoomToCleanList = listByTime
            }
        })
    }
}