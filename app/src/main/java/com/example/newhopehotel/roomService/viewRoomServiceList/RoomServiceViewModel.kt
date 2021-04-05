package com.example.newhopehotel.roomService.viewRoomServiceList

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.database.RoomServiceEntity
import com.example.newhopehotel.utils.provideRepository

class RoomServiceViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var roomServiceList: LiveData<List<RoomServiceEntity>>? = null
        get() {
            return field ?: mRepo.roomServiceList.also { field = it }
        }

    fun insertRoomService(roomService: RoomServiceEntity) {
        mRepo.insertRoomService(roomService)
    }

    fun deleteRoomService(roomService: RoomServiceEntity) {
        mRepo.insertRoomService(roomService)
    }
}