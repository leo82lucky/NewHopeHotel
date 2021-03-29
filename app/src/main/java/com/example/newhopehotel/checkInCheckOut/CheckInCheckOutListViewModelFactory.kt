package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.HotelRepository
import java.lang.IllegalArgumentException

class CheckInCheckOutListViewModelFactory(
    private val repository: HotelRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CheckInCheckOutListViewModel::class.java)) {
            return CheckInCheckOutListViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}