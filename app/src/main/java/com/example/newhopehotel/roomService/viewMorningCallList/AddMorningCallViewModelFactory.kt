package com.example.newhopehotel.roomService.viewMorningCallList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.database.MorningCallEntity

class AddMorningCallViewModelFactory(
    private val mRepo: HotelRepository,
    private val mChosenMorningCall: MorningCallEntity?
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddMorningCallViewModel(mRepo, mChosenMorningCall) as T
    }
}