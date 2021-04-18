package com.example.newhopehotel.roomService.viewRoomServiceList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.database.RoomServiceEntity

class AddRoomServiceViewModelFactory(
    private val mRepo: HotelRepository,
    private val mChosenRoomService: RoomServiceEntity?
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddRoomServiceViewModel(mRepo,mChosenRoomService) as T
    }
}