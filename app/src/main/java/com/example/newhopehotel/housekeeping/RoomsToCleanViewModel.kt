package com.example.newhopehotel.housekeeping

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.*
import com.example.newhopehotel.utils.provideRepository

class RoomsToCleanViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var roomToCleanList: LiveData<List<RoomToCleanEntity>>? = null
        get() {
            return field ?: mRepo.roomToCleanList.also { field = it }
        }

    fun deleteRoomToClean(roomID: RoomID) {
        mRepo.deleteRoomToClean(roomID)
    }

    fun changeRoomToCleanBorderColor(roomID: RoomID, new: Int) {
        mRepo.changeRoomToCleanBorderColor(roomID, new)
    }

    fun insertCleaningList(cleaningList: CleaningListEntity) {
        mRepo.insertCleaningList(cleaningList)
    }

    fun updateRoomsAssignedByUserID(userID: Int, roomsAssigned: Int) {
        mRepo.updateRoomsAssignedByUserID(userID, roomsAssigned)
    }
}