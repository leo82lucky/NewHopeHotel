package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.RegisterRepository
import java.lang.IllegalArgumentException

class CheckInViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CheckInViewModel::class.java)) {
            return CheckInViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}