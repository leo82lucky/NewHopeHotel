package com.example.newhopehotel.userDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newhopehotel.database.HotelRepository

class UserDetailsViewModel(
    repository: HotelRepository,
    application: Application
) : AndroidViewModel(application) {

    val users = repository.users

    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    fun doneNavigating() {
        _navigateto.value = false
    }

    fun backButtonclicked() {
        _navigateto.value = true
    }

}