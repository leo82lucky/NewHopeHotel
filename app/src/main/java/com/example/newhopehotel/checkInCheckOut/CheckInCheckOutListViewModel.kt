package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CheckInCheckOutListViewModel(
    repository: HotelRepository,
    application: Application
) : AndroidViewModel(application), Observable {

    val customers = repository.customers

    //private var customerLiveData = MutableLiveData<CheckInCheckOutEntity?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoCheckIn = MutableLiveData<Boolean>()

    val navigatetoCheckIn: LiveData<Boolean>
        get() = _navigatetoCheckIn

    private val _navigatetoCheckOut = MutableLiveData<Boolean>()

    val navigatetoCheckOut: LiveData<Boolean>
        get() = _navigatetoCheckOut

//    private var _showSnackbarEvent = MutableLiveData<Boolean>()
//
//    val showSnackBarEvent: LiveData<Boolean>
//        get() = _showSnackbarEvent


    fun checkInButton() {
        _navigatetoCheckIn.value = true
    }

    fun checkOutButton() {
        _navigatetoCheckOut.value = true
    }

//    fun ClearButton() {
//        uiScope.launch {
//            delete()
//            _showSnackbarEvent.value = true
//        }
//    }

    fun doneNavigatingCheckIn() {
        _navigatetoCheckIn.value = false
    }

    fun doneNavigatingCheckOut() {
        _navigatetoCheckOut.value = false
    }

//    fun doneShowingSnackbar() {
//        _showSnackbarEvent.value = false
//    }

//    private fun delete(): Job = viewModelScope.launch {
//        repository.deleteAllCustomer()
//    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}