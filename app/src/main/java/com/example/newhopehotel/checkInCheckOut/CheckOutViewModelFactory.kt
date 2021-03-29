package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.HotelRepository
import java.lang.IllegalArgumentException

class CheckOutViewModelFactory(
    private val repository: HotelRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CheckOutViewModel::class.java)) {
            return CheckOutViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}