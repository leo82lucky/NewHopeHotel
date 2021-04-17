package com.example.newhopehotel.homePage

import com.example.newhopehotel.database.HotelRepository
import androidx.databinding.Observable
import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomePageViewModel(repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val users = repository.users
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoUserDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoUserDetails

    fun userDetailsButton() {
        _navigatetoUserDetails.value = true
    }

    fun doneNavigatingUserDetails() {
        _navigatetoUserDetails.value = false
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}