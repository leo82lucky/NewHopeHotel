package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import com.example.newhopehotel.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CheckOutViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}