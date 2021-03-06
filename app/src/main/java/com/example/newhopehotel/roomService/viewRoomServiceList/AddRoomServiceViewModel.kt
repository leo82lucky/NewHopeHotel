package com.example.newhopehotel.roomService.viewRoomServiceList

import androidx.lifecycle.ViewModel
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.database.RoomServiceEntity

class AddRoomServiceViewModel(
    private val mRepo: HotelRepository,
    private val chosenRoomService: RoomServiceEntity?
) : ViewModel() {
    val  roomServiceBeingModified: RoomServiceEntity

    private var mIsEdit: Boolean = false


    init {
        if (chosenRoomService!= null) {
            roomServiceBeingModified =chosenRoomService.copy()
            mIsEdit = true
        } else {
            roomServiceBeingModified = emptyRoomService
            mIsEdit = false
        }
    }

    private fun insertRoomService(roomService: RoomServiceEntity) {
        mRepo.insertRoomService(roomService)
    }

    private fun updateRoomService(roomService: RoomServiceEntity) {
        mRepo.updateRoomService(roomService)
    }

    fun saveRoomService() {
        if (!mIsEdit) {
            insertRoomService(roomServiceBeingModified)
        } else {
            updateRoomService(roomServiceBeingModified)
        }
    }

    private val emptyRoomService: RoomServiceEntity
        get() {
            return RoomServiceEntity(
                custName = "",
                request = "",
                roomNo=""

            )
        }


    val isChanged: Boolean
        get() = if (mIsEdit) roomServiceBeingModified != chosenRoomService
        else roomServiceBeingModified != emptyRoomService

}