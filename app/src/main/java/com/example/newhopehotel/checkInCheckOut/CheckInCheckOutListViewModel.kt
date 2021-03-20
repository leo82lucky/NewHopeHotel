package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newhopehotel.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CheckInCheckOutListViewModel(
    private val repository: RegisterRepository,
    application: Application
) :
    AndroidViewModel(application), Observable {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoCheckIn = MutableLiveData<Boolean>()

    val navigatetoCheckIn: LiveData<Boolean>
        get() = _navigatetoCheckIn

    private val _navigatetoCheckOut = MutableLiveData<Boolean>()

    val navigatetoCheckOut: LiveData<Boolean>
        get() = _navigatetoCheckOut

    fun checkInButton() {
        _navigatetoCheckIn.value = true
    }

    fun checkOutButton() {
        _navigatetoCheckOut.value = true
    }

    fun doneNavigatingCheckIn() {
        _navigatetoCheckIn.value = false
    }

    fun doneNavigatingCheckOut() {
        _navigatetoCheckOut.value = false
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}