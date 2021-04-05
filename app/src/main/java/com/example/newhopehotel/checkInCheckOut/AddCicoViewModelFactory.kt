package com.example.newhopehotel.checkInCheckOut

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository

class AddCicoViewModelFactory(
    private val mRepo: HotelRepository,
    private val mChosenCico: CheckInCheckOutEntity?
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddCicoViewModel(mRepo, mChosenCico) as T
    }
}