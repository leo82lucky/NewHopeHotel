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

    var roomToClean8am: LiveData<List<RoomToCleanEntity>>? = null
        get() {
            return field ?: mRepo.getRoomToCleanByTime("8am").also { field = it }
        }

    var roomToClean12pm: LiveData<List<RoomToCleanEntity>>? = null
        get() {
            return field ?: mRepo.getRoomToCleanByTime("12pm").also { field = it }
        }

    var roomToClean4pm: LiveData<List<RoomToCleanEntity>>? = null
        get() {
            return field ?: mRepo.getRoomToCleanByTime("4pm").also { field = it }
        }

    var roomToClean8pm: LiveData<List<RoomToCleanEntity>>? = null
        get() {
            return field ?: mRepo.getRoomToCleanByTime("8pm").also { field = it }
        }

    fun changeRoomToCleanBorderColor(roomID: RoomID, new: Int) {
        mRepo.changeRoomToCleanBorderColor(roomID, new)
    }

    fun insertCleaningList(cleaningList: CleaningListEntity) {
        mRepo.insertCleaningList(cleaningList)
    }
}